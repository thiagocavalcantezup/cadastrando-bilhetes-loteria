package br.com.zup.handora.cadastrandobilhetesloteria.bilhete;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(SorteioController.BASE_URI)
public class SorteioController {

    public final static String BASE_URI = "/sorteios";

    private final SorteioRepository sorteioRepository;
    private final BilheteRepository bilheteRepository;

    public SorteioController(SorteioRepository sorteioRepository,
                             BilheteRepository bilheteRepository) {
        this.sorteioRepository = sorteioRepository;
        this.bilheteRepository = bilheteRepository;
    }

    @Transactional
    @PostMapping("/{sorteioId}/bilhetes")
    public ResponseEntity<?> createBilhete(@RequestBody @Valid BilheteRequest bilheteRequest,
                                           @PathVariable Long sorteioId, UriComponentsBuilder ucb) {
        Sorteio sorteio = sorteioRepository.findById(sorteioId)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um sorteio com o id fornecido."
                                               )
                                           );

        byte[] hash = TelefoneUtils.hash(bilheteRequest.getTelefoneCelular());

        if (bilheteRepository.existsByHashTelefoneCelularAndNumeroDaSorteAndSorteioId(
            hash, bilheteRequest.getNumeroDaSorte(), sorteioId
        )) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Já existe um bilhete registrado para esse telefone e sorteio."
            );
        }

        Bilhete bilhete = bilheteRequest.toModel();
        sorteio.adicionar(bilhete);

        sorteioRepository.flush();

        URI location = ucb.path(BASE_URI + "/{sorteioId}/bilhetes/{id}")
                          .buildAndExpand(sorteioId, bilhete.getId())
                          .toUri();

        return ResponseEntity.created(location).build();
    }

}

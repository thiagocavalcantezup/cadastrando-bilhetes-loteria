package br.com.zup.handora.cadastrandobilhetesloteria.bilhete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BilheteRepository extends JpaRepository<Bilhete, Long> {

    boolean existsByHashTelefoneCelularAndNumeroDaSorteAndSorteioId(byte[] hash,
                                                                    Integer numeroDaSorte,
                                                                    Long sorteioId);

}

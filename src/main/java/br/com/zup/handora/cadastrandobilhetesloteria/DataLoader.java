package br.com.zup.handora.cadastrandobilhetesloteria;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.handora.cadastrandobilhetesloteria.bilhete.Sorteio;
import br.com.zup.handora.cadastrandobilhetesloteria.bilhete.SorteioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final SorteioRepository sorteioRepository;

    public DataLoader(SorteioRepository sorteioRepository) {
        this.sorteioRepository = sorteioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (sorteioRepository.count() == 0) {
            load();
        }
    }

    private void load() {
        Sorteio sorteio = new Sorteio("Mega da Virada", LocalDateTime.now().plusMonths(2));

        sorteioRepository.save(sorteio);
    }

}

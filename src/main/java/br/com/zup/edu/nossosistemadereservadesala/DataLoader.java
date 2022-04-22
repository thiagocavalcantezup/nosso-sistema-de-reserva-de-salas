package br.com.zup.edu.nossosistemadereservadesala;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.nossosistemadereservadesala.sala.Sala;
import br.com.zup.edu.nossosistemadereservadesala.sala.SalaRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final SalaRepository salaRepository;

    public DataLoader(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (salaRepository.count() == 0) {
            load();
        }
    }

    private void load() {
        Sala sala1 = new Sala("Sala A1");
        Sala sala2 = new Sala("Sala A2");

        salaRepository.save(sala1);
        salaRepository.save(sala2);
    }

}

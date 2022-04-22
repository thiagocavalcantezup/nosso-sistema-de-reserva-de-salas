package br.com.zup.edu.nossosistemadereservadesala.sala;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(SalaController.BASE_URI)
public class SalaController {

    public final static String BASE_URI = "/salas";

    private final SalaRepository salaRepository;

    public SalaController(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Transactional
    @PatchMapping("/{salaId}")
    public ResponseEntity<Void> reservar(@PathVariable Long salaId) {
        Sala sala = salaRepository.findById(salaId)
                                  .orElseThrow(
                                      () -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND,
                                          "Não existe uma sala com o id fornecido."
                                      )
                                  );

        sala.reservar();

        return ResponseEntity.noContent().build();
    }

}

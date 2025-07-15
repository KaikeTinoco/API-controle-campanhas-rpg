package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import com.API.Controle.Rpg.api.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personagens")
public class PersonagemController {
    private final PersonagemService service;

    @Autowired
    public PersonagemController(PersonagemService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<Personagem> criarPersonagem(@RequestBody PersonagemDTO dto,
                                                      @RequestParam Long campanhaId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPersonagem(dto, campanhaId));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Personagem>> buscarPersonagens(@RequestParam(required = false) Long campanhaId,
                                                              @RequestParam(required = false) String nomeCampanha){
        return ResponseEntity.status(HttpStatus.OK).body(service.checarParametros(campanhaId, nomeCampanha));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Personagem> atualizarPersonagem(@RequestParam String nomeCampanha,
                                                          @RequestBody Personagem personagemAtualizado){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPersonagem(nomeCampanha, personagemAtualizado));
    }

    @GetMapping("/busca")
    public ResponseEntity<Personagem> buscarPersonagem(@RequestParam String nomeCampanha,
                                                       @RequestParam String nomePersonagem){
        return ResponseEntity.status(HttpStatus.OK).body(service.acharPersonagemPorNome(nomePersonagem, nomeCampanha));
    }

}

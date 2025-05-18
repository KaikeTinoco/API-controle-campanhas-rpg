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

    @GetMapping("/buscar")
    public ResponseEntity<List<Personagem>> buscarPersonagens(@RequestParam(required = false) Long campanhaId,
                                                              @RequestParam(required = false) String nomeCampanha){
        return ResponseEntity.status(HttpStatus.OK).body(service.checarParametros(campanhaId, nomeCampanha));
    }
}

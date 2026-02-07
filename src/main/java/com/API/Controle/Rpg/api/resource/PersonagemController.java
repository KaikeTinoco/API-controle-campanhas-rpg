package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import com.API.Controle.Rpg.api.services.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
@Tag(name = "Personagens", description = "Gerenciador de Personagens")
public class PersonagemController {
    private final PersonagemService service;

    @Autowired
    public PersonagemController(PersonagemService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cadastro de Personagens",
            description = "Recebe um dto gerado pela IA responsável por essa tarefa e o ID da campanha, registrando o personagem")
    public ResponseEntity<Personagem> criarPersonagem(@RequestBody PersonagemDTO dto,
                                                      @RequestParam Long campanhaId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPersonagem(dto, campanhaId));
    }

    @GetMapping("/buscarTodos")
    @Operation(summary = "Buscar Todos os Personagens",
    description = "Busca todos os personagens cadastrados em uma campanha, usando o nome ou o id dela")
    public ResponseEntity<List<Personagem>> buscarPersonagens(@RequestParam(required = false) Long campanhaId,
                                                              @RequestParam(required = false) String nomeCampanha){
        return ResponseEntity.status(HttpStatus.OK).body(service.checarParametros(campanhaId, nomeCampanha));
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar Personagem",
    description = "Atualiza o personagem da campanha, sendo jogador ou NPC, recebendo um novo objeto e substituindo ele no lugar do antigo")
    public ResponseEntity<Personagem> atualizarPersonagem(@RequestParam String nomeCampanha,
                                                          @RequestBody Personagem personagemAtualizado){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPersonagem(nomeCampanha, personagemAtualizado));
    }

    @GetMapping("/busca")
    @Operation(summary = "Buscar Personagem Específico",
    description = "Busca um personagem específico usando o nome dele e o nome da campanha")
    public ResponseEntity<Personagem> buscarPersonagem(@RequestParam String nomeCampanha,
                                                       @RequestParam String nomePersonagem){
        return ResponseEntity.status(HttpStatus.OK).body(service.acharPersonagemPorNome(nomePersonagem, nomeCampanha));
    }

}

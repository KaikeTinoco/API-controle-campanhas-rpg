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

    /**
     * <p>
     * Registra um novo personagem (Jogador ou NPC) vinculado a uma campanha ativa.
     * Os dados são geralmente gerados pela IA para garantir compatibilidade com o cenário.
     * </p>
     * @param dto Ficha técnica do personagem.
     * @param campanhaId ID da campanha onde o personagem será inserido.
     * @return O {@link Personagem} salvo no banco.
     */
    @PostMapping("/criar")
    @Operation(summary = "Cadastro de Personagens",
            description = "Recebe um dto gerado pela IA responsável por essa tarefa e o ID da campanha, registrando o personagem")
    public ResponseEntity<Personagem> criarPersonagem(@RequestBody PersonagemDTO dto,
                                                      @RequestParam Long campanhaId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPersonagem(dto, campanhaId));
    }

    /**
     * <p>
     * Lista personagens de uma campanha. Permite flexibilidade na busca por ID ou Nome.
     * </p>
     * @param campanhaId (Opcional) ID da campanha.
     * @param nomeCampanha (Opcional) Nome da campanha.
     * @return Lista de personagens pertencentes à campanha especificada.
     */
    @GetMapping("/buscarTodos")
    @Operation(summary = "Buscar Todos os Personagens",
            description = "Busca todos os personagens cadastrados em uma campanha, usando o nome ou o id dela")
    public ResponseEntity<List<Personagem>> buscarPersonagens(@RequestParam(required = false) Long campanhaId,
                                                              @RequestParam(required = false) String nomeCampanha){
        return ResponseEntity.status(HttpStatus.OK).body(service.checarParametros(campanhaId, nomeCampanha));
    }

    /**
     * <p>
     * Atualiza integralmente os dados de um personagem.
     * </p>
     * @param nomeCampanha Contexto da campanha para validação.
     * @param personagemAtualizado Novo objeto de personagem com as alterações.
     * @return Personagem atualizado.
     */
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar Personagem",
            description = "Atualiza o personagem da campanha, sendo jogador ou NPC, recebendo um novo objeto e substituindo ele no lugar do antigo")
    public ResponseEntity<Personagem> atualizarPersonagem(@RequestParam String nomeCampanha,
                                                          @RequestBody Personagem personagemAtualizado){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPersonagem(nomeCampanha, personagemAtualizado));
    }

    /**
     * <p>
     * Busca um personagem específico por nome dentro de uma campanha.
     * </p>
     * @param nomeCampanha Nome da campanha.
     * @param nomePersonagem Nome do personagem.
     * @return Detalhes do personagem encontrado.
     */
    @GetMapping("/busca")
    @Operation(summary = "Buscar Personagem Específico",
            description = "Busca um personagem específico usando o nome dele e o nome da campanha")
    public ResponseEntity<Personagem> buscarPersonagem(@RequestParam String nomeCampanha,
                                                       @RequestParam String nomePersonagem){
        return ResponseEntity.status(HttpStatus.OK).body(service.acharPersonagemPorNome(nomePersonagem, nomeCampanha));
    }

}

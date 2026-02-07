package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.dtos.ResumoDTO;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.services.CampanhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campanha")
@Tag(name = "Campanha", description = "Gerenciador de Campanhas")
public class CampanhaController {
    private final CampanhaService service;

    @Autowired
    public CampanhaController(CampanhaService service) {
        this.service = service;
    }

    /**
     * <p>
     * Endpoint para criação de novas campanhas.
     * Recebe os dados processados pelo bot externo e inicia uma nova jornada no banco de dados.
     * </p>
     * @param dto Objeto contendo nome, sistema e descrição da campanha.
     * @return ResponseEntity com a {@link Campanha} criada e status 201 (Created).
     */
    @PostMapping("/criar")
    @Operation(summary = "Criar Campanha",
            description = "Cria a campanha com base num DTO enviado pelo bot gerador de história")
    public ResponseEntity<Campanha> criarCampanha(@RequestBody CampanhaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCampanha(dto));
    }

    /**
     * <p>
     * Recupera todas as campanhas existentes. Útil para painéis de mestre ou seleção de mesas.
     * </p>
     * @return Lista de todas as campanhas cadastradas.
     */
    @GetMapping("/buscaGeral")
    @Operation(summary = "Buscar Todas as Campanhas", description = "Lista todas as campanhas registradas")
    public ResponseEntity<List<Campanha>> buscarTodasCampanhas(){
        return ResponseEntity.ok(service.buscarTodasCampanhas());
    }

    /**
     * <p>
     * Filtra uma campanha através do seu nome único.
     * </p>
     * @param nome Nome da campanha desejada.
     * @return A entidade encontrada.
     */
    @GetMapping("/buscaPorNome")
    @Operation(summary = "Buscar Campanha por Nome", description = "Busca uma campanha por nome")
    public ResponseEntity<Campanha> buscarCampanhaPorNome(@RequestParam String nome){
        return ResponseEntity.ok(service.buscarCampanhaPorNome(nome));
    }

    /**
     * <p>
     * Lista todas as campanhas que utilizam um sistema específico (ex: D&D 5e, Pathfinder).
     * </p>
     * @param sistema Nome do sistema de regras.
     * @return Lista de campanhas filtradas por sistema.
     */
    @GetMapping("/buscaPorSistema")
    @Operation(summary = "Buscar Campanha por Sistema", description = "Busca uma campanha por Sistema")
    public ResponseEntity<List<Campanha>> buscarCampanhaPorSistema(@RequestParam String sistema){
        return ResponseEntity.ok(service.buscarCampanhaPorSistema(sistema));
    }

    /**
     * <p>
     * Remove permanentemente uma campanha do sistema.
     * </p>
     * @param id Identificador único da campanha.
     * @return Mensagem de confirmação da exclusão.
     */
    @DeleteMapping("/deletar")
    @Operation(summary = "Deletar Campanha", description = "Deleta uma campanha via ID")
    public ResponseEntity<String> deletarCampanha(@RequestParam Long id){
        return ResponseEntity.ok(service.deletarCampanha(id));
    }

    /**
     * <p>
     * Atualiza o estado da campanha com base em um resumo de sessão enviado pelo bot.
     * Este endpoint é vital para manter o contexto histórico atualizado para a IA.
     * </p>
     * @param dto Contém o resumo da história e o nome da campanha.
     * @return Mensagem de sucesso na atualização.
     */
    @PostMapping("/atualizar")
    @Operation(summary = "Salvar Campanha",
            description = "Recebe um DTO básico que contém uma descrição do que aconteceu na sessão e o ID da campanha")
    public ResponseEntity<String> salvarSessao(@RequestBody ResumoDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.salvarSessao(dto));
    }


}

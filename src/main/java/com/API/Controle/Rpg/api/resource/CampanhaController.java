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

    @PostMapping("/criar")
    @Operation(summary = "Criar Campanha",
    description = "Cria a campanha com base num DTO enviado pelo bot gerador de história")
    public ResponseEntity<Campanha> criarCampanha(@RequestBody CampanhaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCampanha(dto));
    }

    @GetMapping("/buscaGeral")
    @Operation(summary = "Buscar Todas as Campanhas", description = "Lista todas as campanhas registradas")
    public ResponseEntity<List<Campanha>> buscarTodasCampanhas(){
        return ResponseEntity.ok(service.buscarTodasCampanhas());
    }

    @GetMapping("/buscaPorNome")
    @Operation(summary = "Buscar Campanha por Nome", description = "Busca uma campanha por nome")
    public ResponseEntity<Campanha> buscarCampanhaPorNome(@RequestParam String nome){
        return ResponseEntity.ok(service.buscarCampanhaPorNome(nome));
    }

    @GetMapping("/buscaPorSistema")
    @Operation(summary = "Buscar Campanha por Sistema", description = "Busca uma campanha por Sistema")
    public ResponseEntity<List<Campanha>> buscarCampanhaPorSistema(@RequestParam String sistema){
        return ResponseEntity.ok(service.buscarCampanhaPorSistema(sistema));
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Deletar Campanha", description = "Deleta uma campanha via ID")
    public ResponseEntity<String> deletarCampanha(@RequestParam Long id){
        return ResponseEntity.ok(service.deletarCampanha(id));
    }

    @PostMapping("/atualizar")
    @Operation(summary = "Salvar Campanha",
            description = "Recebe um DTO básico que contém uma descrição do que aconteceu na sessão e o ID da campanha")
    public ResponseEntity<String> salvarSessao(@RequestBody ResumoDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.salvarSessao(dto));
    }


}

package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.services.CampanhaService;
import lombok.Getter;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {
    private final CampanhaService service;

    @Autowired
    public CampanhaController(CampanhaService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<Campanha> criarCampanha(@RequestBody CampanhaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCampanha(dto));
    }

    @GetMapping("/buscaGeral")
    public ResponseEntity<List<Campanha>> buscarTodasCampanhas(){
        return ResponseEntity.ok(service.buscarTodasCampanhas());
    }

    @GetMapping("/buscaPorNome")
    public ResponseEntity<Campanha> buscarCampanhaPorNome(@RequestParam String nome){
        return ResponseEntity.ok(service.buscarCampanhaPorNome(nome));
    }

    @GetMapping("/buscaPorSistema")
    public ResponseEntity<List<Campanha>> buscarCampanhaPorSistema(@RequestParam String sistema){
        return ResponseEntity.ok(service.buscarCampanhaPorSistema(sistema));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarCampanha(@RequestParam Long id){
        return ResponseEntity.ok(service.deletarCampanha(id));
    }


}

package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.services.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {
    private final CampanhaService service;

    @Autowired
    public CampanhaController(CampanhaService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public void criarCampanha(@RequestBody CampanhaDTO dto){
        service.criarCampanha(dto);
    }


}

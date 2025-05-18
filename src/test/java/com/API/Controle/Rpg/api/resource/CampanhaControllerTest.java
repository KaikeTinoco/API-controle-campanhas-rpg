package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.services.CampanhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CampanhaControllerTest {
    @InjectMocks
    private CampanhaController controller;

    @Mock
    private CampanhaService service;

    private Campanha campanha;

    private CampanhaDTO dto;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        campanha = Campanha.builder()
                .id(1L)
                .nome("teste")
                .sistema("teste")
                .descricaoHistoria("teste")
                .contextoNarrativoAtual(new ArrayList<>())
                .personagemList(new ArrayList<>())
                .status(StatusCampanha.ATIVA)
                .dataCriacao(LocalDateTime.now())
                .ultimaAtualizacao(LocalDateTime.now())
                .notasPublicas(new ArrayList<>())
                .notasPrivadas(new ArrayList<>())
                .logDeEventos(new ArrayList<>())
                .restricoes(new ArrayList<>())
                .build();

        dto = CampanhaDTO.builder()
                .nome("teste")
                .descricaoHistoria("teste")
                .restricoes(new ArrayList<>())
                .sistema("teste")
                .build();
    }

    @Test
    void criarCampanha() {
        Campanha c = campanha;
        Mockito.when(this.service.criarCampanha(dto)).thenReturn(c);
        ResponseEntity response = controller.criarCampanha(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarTodasCampanhas() {
        Campanha c = campanha;
        Mockito.when(this.service.buscarTodasCampanhas()).thenReturn(Arrays.asList(c));
        ResponseEntity response = controller.buscarTodasCampanhas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarCampanhaPorNome() {
        Campanha c = campanha;
        Mockito.when(this.service.buscarCampanhaPorNome("teste")).thenReturn(c);
        ResponseEntity response = controller.buscarCampanhaPorNome("teste");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarCampanhaPorSistema() {
        Campanha c = campanha;
        Mockito.when(this.service.buscarCampanhaPorSistema("teste")).thenReturn(Arrays.asList(c));
        ResponseEntity response = controller.buscarCampanhaPorSistema("teste");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void deletarCampanha() {
        Campanha c = campanha;
        Mockito.when(this.service.criarCampanha(dto)).thenReturn(c);
        Mockito.when(this.service.deletarCampanha(c.getId())).thenReturn("Deletado com sucesso!");
        ResponseEntity response = controller.deletarCampanha(c.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
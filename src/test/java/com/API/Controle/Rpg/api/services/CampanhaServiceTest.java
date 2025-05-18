package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.repositories.CampanhaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.listeners.MockitoListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CampanhaServiceTest {
    @InjectMocks
    private CampanhaService service;

    @Mock
    private CampanhaRepository repository;

    Campanha cAmpanha;
    CampanhaDTO dto;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        cAmpanha = Campanha.builder()
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
            Mockito.when(this.service.criarCampanha(dto)).thenReturn(cAmpanha);
            Mockito.when(this.repository.save(cAmpanha)).thenReturn(cAmpanha);
            Campanha c;
            c = service.criarCampanha(dto);
            assertEquals(c.getNome(), cAmpanha.getNome());
            assertEquals(c.getSistema(), cAmpanha.getSistema());
            assertEquals(c.getDescricaoHistoria(), cAmpanha.getDescricaoHistoria());
            assertEquals(c.getStatus(), cAmpanha.getStatus());
    }

    @Test
    void buscarTodasCampanhas() {
        Mockito.when(this.service.criarCampanha(dto)).thenReturn(cAmpanha);
        Mockito.when(this.repository.save(cAmpanha)).thenReturn(cAmpanha);
        Campanha c;
        c = service.criarCampanha(dto);
        Mockito.when(this.repository.findAll()).thenReturn(Arrays.asList(c));
        Mockito.when(this.service.buscarTodasCampanhas()).thenReturn(Arrays.asList(c));
        List<Campanha> l = service.buscarTodasCampanhas();
        assertEquals(c.getNome(), l.get(0).getNome());
        assertEquals(c.getSistema(), l.get(0).getSistema());
        assertEquals(c.getDescricaoHistoria(), l.get(0).getDescricaoHistoria());
        assertEquals(c.getStatus(), l.get(0).getStatus());
    }

    @Test
    void buscarCampanhaPorNome() {
        Mockito.when(this.service.criarCampanha(dto)).thenReturn(cAmpanha);
        Mockito.when(this.repository.save(cAmpanha)).thenReturn(cAmpanha);
        Campanha c;
        c = service.criarCampanha(dto);
        Mockito.when(this.repository.findByNome("teste")).thenReturn(Optional.of(c));
        Campanha cFound = service.buscarCampanhaPorNome("teste");
        assertEquals(c.getNome(), cFound.getNome());
        assertEquals(c.getSistema(), cFound.getSistema());
        assertEquals(c.getDescricaoHistoria(), cFound.getDescricaoHistoria());
        assertEquals(c.getStatus(), cFound.getStatus());
    }

    @Test
    void buscarCampanhaPorSistema() {
        Mockito.when(this.service.criarCampanha(dto)).thenReturn(cAmpanha);
        Mockito.when(this.repository.save(cAmpanha)).thenReturn(cAmpanha);
        Campanha c;
        c = service.criarCampanha(dto);
        Mockito.when(this.repository.findBySistema("teste")).thenReturn(Optional.of(Arrays.asList(c)));
        List<Campanha> cFound = service.buscarCampanhaPorSistema("teste");
        assertEquals(c.getNome(), cFound.get(0).getNome());
        assertEquals(c.getSistema(), cFound.get(0).getSistema());
        assertEquals(c.getDescricaoHistoria(), cFound.get(0).getDescricaoHistoria());
        assertEquals(c.getStatus(), cFound.get(0).getStatus());
    }

    @Test
    void deletarCampanha() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(cAmpanha));
        Campanha c = cAmpanha;
        String mensagem = service.deletarCampanha(c.getId());
        assertEquals("Deletado com sucesso!", mensagem);
    }
}
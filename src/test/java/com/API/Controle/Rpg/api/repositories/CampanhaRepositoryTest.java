package com.API.Controle.Rpg.api.repositories;

import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.services.CampanhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CampanhaRepositoryTest {
   

    @Autowired
    private CampanhaRepository repository;

    private Campanha campanha;

    @BeforeEach
    void init(){
        campanha = Campanha.builder()
                .nome("Campanha teste")
                .sistema("Dnd")
                .descricaoHistoria("teste")
                .contextoNarrativoAtual(new ArrayList<>())
                .personagemList(new ArrayList<>())
                .status(StatusCampanha.ATIVA)
                .dataCriacao(LocalDateTime.now())
                .ultimaAtualizacao(LocalDateTime.now())
                .notasPrivadas(new ArrayList<>())
                .notasPrivadas(new ArrayList<>())
                .logDeEventos(new ArrayList<>())
                .restricoes(new ArrayList<>())
                .build();
    }

    @Test
    void saveCampanha(){
        Campanha c = campanha;
        repository.save(c);
        assertEquals("Campanha teste", c.getNome());
        assertEquals("Dnd", c.getSistema());
        assertEquals("teste",c.getDescricaoHistoria());
        assertEquals(StatusCampanha.ATIVA, c.getStatus());
        assertNotNull(c.getId());
    }

    @Test
    void findByNome() {

    }

    @Test
    void findBySistema() {
    }
}
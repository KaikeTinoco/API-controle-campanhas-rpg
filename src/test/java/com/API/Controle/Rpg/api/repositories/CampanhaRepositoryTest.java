package com.API.Controle.Rpg.api.repositories;

import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CampanhaRepositoryTest {
    @Autowired
    private CampanhaRepository repository;

    Campanha cAmpanha;
    @BeforeEach
    void init(){
      cAmpanha = Campanha.builder()
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

    }
    @Test
    void saveCampanha(){
        Campanha c = cAmpanha;
        repository.save(c);
        assertEquals("teste",c.getNome());
        assertEquals("teste",c.getDescricaoHistoria());
        assertEquals("teste",c.getSistema());
        assertEquals(StatusCampanha.ATIVA, c.getStatus());
        assertNotNull(c.getId());
    }

    @Test
    void findByNome() {
        Campanha c = cAmpanha;
        repository.save(c);
        Campanha cFound = repository.findByNome("teste").get();
        assertEquals(c, cFound);
    }

    @Test
    void findBySistema() {
        List<Campanha> c = Arrays.asList(cAmpanha);
        repository.saveAll(c);
        List<Campanha> cFound = repository.findBySistema("teste").get();
        assertEquals(c, cFound);
    }
}
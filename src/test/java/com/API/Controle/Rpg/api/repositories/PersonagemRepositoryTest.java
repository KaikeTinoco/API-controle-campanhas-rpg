package com.API.Controle.Rpg.api.repositories;

import com.API.Controle.Rpg.api.domain.enums.Sexo;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.enums.Tendencia;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PersonagemRepositoryTest {
    @Autowired
    private PersonagemRepository repository;

    @Autowired
    private CampanhaRepository campanhaRepository;


    Campanha campanha;

    Personagem personagem;
    @BeforeEach
    void init(){
        campanha = Campanha.builder()
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

        personagem = Personagem.builder()
                .nome("teste")
                .idade(125)
                .sexo(Sexo.MASCULINO)
                .nivel(3)
                .classe("Druida")
                .subclasse("Mestre dos animais")
                .tendencia(Tendencia.NEUTRO_BOM)
                .forca(20)
                .destreza(20)
                .constituicao(20)
                .inteligencia(20)
                .sabedoria(20)
                .carisma(20)
                .modForca(2)
                .modDestreza(2)
                .modConstituicao(2)
                .modInteligencia(2)
                .modSabedoria(2)
                .modCarisma(2)
                .pontosDeVida(25)
                .dadoDeVida("3d10 + 5")
                .classeArmadura(20)
                .cdMagia(2)
                .modAtaqueMagico(2)
                .espacosMagiaNivelUm(2)
                .espacosMagiaNivelDois(2)
                .magiasTruques(Arrays.asList("magias"))
                .magiasNivelUm(Arrays.asList("magias"))
                .magiasNivelDois(Arrays.asList("magias"))
                .equipamentos(Arrays.asList("equipamentos"))
                .idiomas(Arrays.asList("idiomas"))
                .ferramentas(Arrays.asList("ferramentas"))
                .salvaguardas(Arrays.asList("ferramentas"))
                .tracosDePersonalidade(Arrays.asList("ferramentas"))
                .ideais(Arrays.asList("ferramentas"))
                .vinculo("vinculo")
                .defeito("defeito")
                .isNpc(false)
                .build();
    }

    @Test
    void findByNome() {
        Personagem p = personagem;
        Campanha c = campanha;
        campanhaRepository.save(c);
        repository.save(p);
        c.adicionarPersonagem(p);
        Personagem personagemFound = repository.findByNome("teste").get();
        assertEquals(p, personagemFound);
    }

    @Test
    void findById() {
        Personagem p = personagem;
        Campanha c = campanha;
        campanhaRepository.save(c);
        repository.save(p);
        c.adicionarPersonagem(p);
        Personagem personagemFound = repository.findById(p.getId()).get();
        assertEquals(p, personagemFound);
    }

    @Test
    void findByIsNpc() {
        Personagem p = personagem;
        Campanha c = campanha;
        campanhaRepository.save(c);
        repository.save(p);
        c.adicionarPersonagem(p);
        List<Personagem> personagems = repository.findByIsNpc(false).get();
        assertEquals(p, personagems.get(0));
    }

    @Test
    void save(){
        Personagem p = personagem;
        Campanha c = campanha;
        campanhaRepository.save(c);
        repository.save(p);
        assertNotNull(p.getId());
    }

    @Test
    void delete(){
        Personagem p = personagem;
        Campanha c = campanha;
        campanhaRepository.save(c);
        repository.save(p);
        Long id = p.getId();
        repository.delete(p);
        assertEquals(Optional.empty(), repository.findById(id));
    }
}
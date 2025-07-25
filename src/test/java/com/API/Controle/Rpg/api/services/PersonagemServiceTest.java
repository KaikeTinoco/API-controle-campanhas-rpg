package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.enums.Sexo;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.enums.Tendencia;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import com.API.Controle.Rpg.api.repositories.CampanhaRepository;
import com.API.Controle.Rpg.api.repositories.PersonagemRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonagemServiceTest {
    @InjectMocks
    PersonagemService service;

    @Mock
    PersonagemRepository repository;

    @Mock
    CampanhaService campanhaService;

    @Mock
    CampanhaRepository campanhaRepository;

    Campanha campanha;

    Personagem personagem, personagemNovo;

    CampanhaDTO dto;

    PersonagemDTO personagemDto;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
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

        dto = CampanhaDTO.builder()
                .nome("teste")
                .descricaoHistoria("teste")
                .restricoes(new ArrayList<>())
                .sistema("teste")
                .build();

        personagemDto = PersonagemDTO.builder()
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
                .npc(false)
                .build();

        personagemNovo = Personagem.builder()
                .nome("novo")
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
    void criarPersonagem() {
        Mockito.when(this.campanhaService.criarCampanha(dto)).thenReturn(campanha);
        Campanha c;
        c = campanhaService.criarCampanha(dto);
        Mockito.doNothing().when(this.campanhaService).adicionarPersonagemNaCampanha(personagem,c);
        Mockito.when(this.repository.save(personagem)).thenReturn(personagem);
        Personagem p = service.criarPersonagem(personagemDto, 1L);
        assertEquals("teste", personagem.getNome());
    }

    @Test
    void listarPersonagensNaCampanhaNome() {
        Mockito.when(this.campanhaService.criarCampanha(dto)).thenReturn(campanha);
        Campanha c = campanhaService.criarCampanha(dto);
        Mockito.when(this.repository.save(personagem)).thenReturn(personagem);
        Personagem p = service.criarPersonagem(personagemDto, 1L);
        c.setPersonagemList(Arrays.asList(p)); //simulando o a adicao do personagem
        Mockito.when(this.campanhaService.buscarCampanhaPorNome(c.getNome())).thenReturn(c);
        Mockito.when(this.campanhaRepository.findByNome(c.getNome())).thenReturn(Optional.of(c));
        List<Personagem> personagens = service.listarPersonagensNaCampanha(c.getNome());
        assertEquals(p, personagens.get(0));
    }

    @Test
    void ListarPersonagensNaCampanhaId() { Mockito.when(this.campanhaService.criarCampanha(dto)).thenReturn(campanha);
        Campanha c = campanhaService.criarCampanha(dto);
        Mockito.when(this.repository.save(personagem)).thenReturn(personagem);
        Personagem p = service.criarPersonagem(personagemDto, 1L);
        c.setPersonagemList(Arrays.asList(p)); //simulando o a adicao do personagem
        Mockito.when(this.campanhaService.findById(1L)).thenReturn(c);
        Mockito.when(this.campanhaRepository.findById(1L)).thenReturn(Optional.of(c));
        List<Personagem> personagens = service.listarPersonagensNaCampanha(1L);
        assertEquals(p, personagens.get(0));
    }


    @Test
    void acharPersonagemPorNome() {
        Mockito.when(this.campanhaService.criarCampanha(dto)).thenReturn(campanha);
        Campanha c = campanhaService.criarCampanha(dto);
        Mockito.when(this.repository.save(personagem)).thenReturn(personagem);
        Personagem p = service.criarPersonagem(personagemDto, 1L);
        c.setPersonagemList(Arrays.asList(p)); //simulando o a adicao do personagem
        Mockito.when(this.campanhaService.buscarCampanhaPorNome(c.getNome())).thenReturn(c);
        Mockito.when(this.campanhaRepository.findByNome(c.getNome())).thenReturn(Optional.of(c));
        Personagem personagemFound = service.acharPersonagemPorNome("teste", c.getNome());
        assertEquals(p, personagemFound);
    }

    @Test
    void atualizarPersonagem() {
        Mockito.when(this.campanhaService.criarCampanha(dto)).thenReturn(campanha);
        Campanha c = campanhaService.criarCampanha(dto);
        Mockito.when(this.repository.save(personagem)).thenReturn(personagem);
        Personagem p = service.criarPersonagem(personagemDto, 1L);
        c.setPersonagemList(Arrays.asList(p)); //simulando o a adicao do personagem
        Mockito.when(this.campanhaService.buscarCampanhaPorNome(c.getNome())).thenReturn(c);
        Personagem personagemAtualizado = service.atualizarPersonagem(c.getNome(), personagemNovo);
        assertEquals("novo", personagemAtualizado.getNome());
    }
}
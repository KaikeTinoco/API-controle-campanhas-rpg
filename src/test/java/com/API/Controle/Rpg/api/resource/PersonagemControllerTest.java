package com.API.Controle.Rpg.api.resource;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.enums.Sexo;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.enums.Tendencia;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import com.API.Controle.Rpg.api.services.PersonagemService;
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

import static org.junit.jupiter.api.Assertions.*;

class PersonagemControllerTest {

    @InjectMocks
    private PersonagemController controller;

    @Mock
    private PersonagemService service;

    private Campanha campanha;

    private PersonagemDTO personagemDto;

    private CampanhaDTO dto;

    private Personagem personagem, personagemNovo;

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
        PersonagemDTO personagemDTO = personagemDto;
        Mockito.when(this.service.criarPersonagem(personagemDTO, 1L)).thenReturn(personagem);
        ResponseEntity response = controller.criarPersonagem(personagemDTO, 1L);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarPersonagens() {
        Campanha c = campanha;
        Personagem p = personagem;
        Mockito.when(this.service.checarParametros(null, c.getNome())).thenReturn(Arrays.asList(p));
        Mockito.when(this.service.listarPersonagensNaCampanha(c.getNome())).thenReturn(Arrays.asList(p));
        ResponseEntity response = controller.buscarPersonagens(null, c.getNome());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void atualizarPersonagem() {
        Campanha c = campanha;
        Personagem p = personagem;
        Personagem pNovo = personagemNovo;
        Mockito.when(this.service.atualizarPersonagem(c.getNome(), pNovo)).thenReturn(pNovo);
        ResponseEntity response = controller.atualizarPersonagem(c.getNome(), pNovo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarPersonagem() {
        Campanha c = campanha;
        Personagem p = personagem;
        Mockito.when(this.service.acharPersonagemPorNome(p.getNome(), c.getNome())).thenReturn(p);
        ResponseEntity response = controller.buscarPersonagem(p.getNome(), c.getNome());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
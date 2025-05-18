package com.API.Controle.Rpg.api.domain.dtos;

import com.API.Controle.Rpg.api.domain.enums.Sexo;
import com.API.Controle.Rpg.api.domain.enums.Tendencia;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonagemDTO {
    private String nome;
    private Integer idade;
    private Sexo sexo;
    private Integer nivel;
    private String classe;
    private String subclasse;

    private Tendencia tendencia;

    private Integer forca;
    private Integer destreza;
    private Integer constituicao;
    private Integer inteligencia;
    private Integer sabedoria;
    private Integer carisma;

    private Integer modForca;
    private Integer modDestreza;
    private Integer modConstituicao;
    private Integer modInteligencia;
    private Integer modSabedoria;
    private Integer modCarisma;

    private Integer pontosDeVida;
    private String dadoDeVida;
    private Integer classeArmadura;

    private Integer cdMagia;
    private Integer modAtaqueMagico;
    private Integer espacosMagiaNivelUm;
    private Integer espacosMagiaNivelDois;

    private List<String> magiasTruques;

    private List<String> magiasNivelUm;

    private List<String> magiasNivelDois;

    private List<String> equipamentos;

    private List<String> proficiencias;

    private List<String> idiomas;

    private List<String> ferramentas;

    private List<String> salvaguardas;

    private List<String> tracosDePersonalidade;

    private List<String> ideais;

    private String vinculo;

    private String defeito;

    private boolean isNpc;

}

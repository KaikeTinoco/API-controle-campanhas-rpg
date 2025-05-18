package com.API.Controle.Rpg.api.domain.model;

import com.API.Controle.Rpg.api.domain.enums.Sexo;
import com.API.Controle.Rpg.api.domain.enums.Tendencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private Sexo sexo;
    private Integer nivel;
    private String classe;
    private String subclasse;
    @Enumerated(EnumType.STRING)
    private Tendencia tendencia;
    @Setter
    @ManyToOne
    @JoinColumn(name = "campanha_id")
    private Campanha campanha;

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

    @ElementCollection
    private List<String> magiasTruques;

    @ElementCollection
    private List<String> magiasNivelUm;

    @ElementCollection
    private List<String> magiasNivelDois;

    @ElementCollection
    private List<String> equipamentos;

    @ElementCollection
    private List<String> proficiencias;

    @ElementCollection
    private List<String> idiomas;

    @ElementCollection
    private List<String> ferramentas;

    @ElementCollection
    private List<String> salvaguardas;

    @ElementCollection
    private List<String> tracosDePersonalidade;

    @ElementCollection
    private List<String> ideais;

    private String vinculo;

    private String defeito;

    private boolean isNpc;

}

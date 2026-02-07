package com.API.Controle.Rpg.api.domain.dtos;

import com.API.Controle.Rpg.api.domain.enums.Sexo;
import com.API.Controle.Rpg.api.domain.enums.Tendencia;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * Objeto de transferência de dados para a entidade {@link com.API.Controle.Rpg.api.domain.model.Personagem}.
 * Contém todos os campos necessários para compor uma ficha de personagem completa,
 * incluindo atributos, modificadores, magias e background narrativo.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemDTO {
    private String nome;
    private Integer idade;
    private Sexo sexo;
    private Integer nivel;
    private String classe;
    private String subclasse;

    private Tendencia tendencia;

    // Atributos base
    private Integer forca;
    private Integer destreza;
    private Integer constituicao;
    private Integer inteligencia;
    private Integer sabedoria;
    private Integer carisma;

    // Modificadores calculados
    private Integer modForca;
    private Integer modDestreza;
    private Integer modConstituicao;
    private Integer modInteligencia;
    private Integer modSabedoria;
    private Integer modCarisma;

    // Status de combate
    private Integer pontosDeVida;
    private String dadoDeVida;
    private Integer classeArmadura;

    // Status mágicos
    private Integer cdMagia;
    private Integer modAtaqueMagico;
    private Integer espacosMagiaNivelUm;
    private Integer espacosMagiaNivelDois;

    // Listas de habilidades e inventário
    private List<String> magiasTruques;
    private List<String> magiasNivelUm;
    private List<String> magiasNivelDois;
    private List<String> equipamentos;
    private List<String> proficiencias;
    private List<String> idiomas;
    private List<String> ferramentas;
    private List<String> salvaguardas;

    // Background e interpretação
    private List<String> tracosDePersonalidade;
    private List<String> ideais;
    private String vinculo;
    private String defeito;

    /** Define se o personagem é um NPC (Non-Player Character) ou um jogador. */
    private boolean npc;
}

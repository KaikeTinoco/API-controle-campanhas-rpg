package com.API.Controle.Rpg.api.domain.dtos;

import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * Objeto de transferência de dados para a entidade {@link com.API.Controle.Rpg.api.domain.model.Campanha}.
 * Utilizado para receber as informações básicas necessárias para a criação ou atualização
 * de uma mesa de RPG.
 * </p>
 */
@Getter
@Setter
@Builder
public class CampanhaDTO {
    private String nome;
    private String sistema;
    private String descricaoHistoria;
    private List<String> restricoes;
}

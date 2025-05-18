package com.API.Controle.Rpg.api.domain.dtos;

import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CampanhaDTO {
    private String nome;
    private String sistema;
    private String descricaoHistoria;
    private List<String> restricoes;
}

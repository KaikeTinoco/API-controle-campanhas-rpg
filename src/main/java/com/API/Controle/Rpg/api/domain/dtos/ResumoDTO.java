package com.API.Controle.Rpg.api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumoDTO {
    private String nomeCampanha;
    private String resumoSessao;
}

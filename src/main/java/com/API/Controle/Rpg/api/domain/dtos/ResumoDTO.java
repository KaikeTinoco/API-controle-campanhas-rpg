package com.API.Controle.Rpg.api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * DTO utilizado para o registro de sessões de jogo.
 * Mapeia o resumo do que aconteceu em uma partida para atualizar o contexto
 * narrativo de uma campanha específica.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumoDTO {
    private String nomeCampanha;
    private String resumoSessao;
}

package com.API.Controle.Rpg.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * DTO padronizado para retorno de erros da API.
 * Utilizado pelos manipuladores de exceção (Global Exception Handler) para fornecer
 * feedback detalhado sobre falhas de validação ou erros internos.
 * </p>
 */
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {
    private Integer statusCode;
    private String message;
    private List<String> messages; // Lista para múltiplos erros (ex: validações de campos)
}

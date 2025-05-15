package com.API.Controle.Rpg.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {
    private Integer statusCode;
    private String message;
    private List<String> messages;
}

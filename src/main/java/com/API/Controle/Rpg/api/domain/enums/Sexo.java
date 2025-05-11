package com.API.Controle.Rpg.api.domain.enums;

public enum Sexo {
    MASCULINO("masculino"),
    FEMINIO("feminino");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

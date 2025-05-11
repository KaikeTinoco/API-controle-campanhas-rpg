package com.API.Controle.Rpg.api.domain.enums;

public enum Tendencia {
    LEAL_BOM("Leal e Bom"),
    NEUTRO_BOM("Neutro e Bom"),
    CAOTICO_BOM("Caótico e Bom"),

    LEAL_NEUTRO("Leal e Neutro"),
    NEUTRO_NEUTRO("Verdadeiramente Neutro"),
    CAOTICO_NEUTRO("Caótico e Neutro"),

    LEAL_MAU("Leal e Mau"),
    NEUTRO_MAU("Neutro e Mau"),
    CAOTICO_MAU("Caótico e Mau");

    private final String descricao;

    Tendencia(String descricao) {
        this.descricao = descricao;
    }

}

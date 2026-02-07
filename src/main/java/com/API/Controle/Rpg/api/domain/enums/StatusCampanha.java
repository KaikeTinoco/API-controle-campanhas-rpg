package com.API.Controle.Rpg.api.domain.enums;

public enum StatusCampanha {
    ATIVA("ATIVA"),
    RASCUNHO("rascunho"),
    ENCERRADA("encerrada");

    private final String descricao;

    StatusCampanha(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}

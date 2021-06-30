package com.tipiniquim.os.enums;

import lombok.Getter;

@Getter
public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer cod;
    private String descricao;

    Status(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (Status x : Status.values()) {
            if (x.cod.equals(cod))
                return x;
        }

        throw new IllegalArgumentException("Status inválido!" + cod);
    }
}

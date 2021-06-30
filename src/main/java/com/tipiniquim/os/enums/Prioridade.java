package com.tipiniquim.os.enums;

import lombok.Getter;

@Getter
public enum Prioridade {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer cod;
    private String descricao;

    Prioridade(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null)
            return null;

        for (Prioridade x : Prioridade.values()) {
            if (x.cod.equals(cod))
                return x;
        }

        throw new IllegalArgumentException("Prioridade inválida!" + cod);
    }
}

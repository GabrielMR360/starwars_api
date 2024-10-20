package br.com.gabrielmarcolino.starwarsapi.model.enums;

public enum GeneroEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String descricao;

    GeneroEnum(String descricao) {
        this.descricao = descricao;
    }
}

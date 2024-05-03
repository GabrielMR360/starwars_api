package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;

public record LocalizacaoRequest(Integer latitude, Integer longitude, String nome) {
    public Localizacao toLocalizacao() {
        return new Localizacao(null, latitude, longitude, nome);
    }
}

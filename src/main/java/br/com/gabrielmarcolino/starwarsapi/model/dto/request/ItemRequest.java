package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import lombok.Data;

@Data
public class ItemRequest {
    private String nome;
    private Integer pontos;
}

package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private String nome;
    private Integer pontos;
}

package br.com.gabrielmarcolino.starwarsapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaRecursosResponse {
    private String nome;
    private Double media;
}

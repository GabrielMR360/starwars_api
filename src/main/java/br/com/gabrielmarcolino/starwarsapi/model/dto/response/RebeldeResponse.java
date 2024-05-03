package br.com.gabrielmarcolino.starwarsapi.model.dto.response;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RebeldeResponse {
    private Long id;
    private String nome;
    private int idade;
    private GeneroEnum genero;
    private Localizacao localizacao;

    public static RebeldeResponse toRebeldeResponse(Rebelde rebelde) {
        return new RebeldeResponse(
                rebelde.getId(),
                rebelde.getNome(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                rebelde.getLocalizacao());
    }
}

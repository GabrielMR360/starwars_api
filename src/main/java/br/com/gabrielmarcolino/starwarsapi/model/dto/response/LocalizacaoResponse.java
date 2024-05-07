package br.com.gabrielmarcolino.starwarsapi.model.dto.response;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalizacaoResponse {
    private Long id;
    private Integer latitude;
    private Integer longitude;
    private String nome;

    public static LocalizacaoResponse toLocalizacaoResponse(Localizacao localizacao) {
        return new LocalizacaoResponse(localizacao.getId(),
                localizacao.getLatitude(),
                localizacao.getLongitude(),
                localizacao.getNome());
    }
}

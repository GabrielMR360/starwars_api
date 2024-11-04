package br.com.gabrielmarcolino.starwarsapi.validation.rebelde;

import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;

public interface RebeldeValidador {
    void validar(Rebelde rebeldeNegociante, Rebelde rebeldeRecebedor);
}

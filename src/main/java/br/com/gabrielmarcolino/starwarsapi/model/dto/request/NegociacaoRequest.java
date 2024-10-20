package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;

import java.util.List;

public record NegociacaoRequest(
        Long idNegociante,
        Long idRecebedor,
        List<InventarioItemRequest> itensNegociante,
        List<InventarioItemRequest> itensRecebedor) {
}

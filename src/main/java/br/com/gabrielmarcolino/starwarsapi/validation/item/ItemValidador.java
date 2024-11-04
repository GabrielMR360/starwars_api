package br.com.gabrielmarcolino.starwarsapi.validation.item;

import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;

import java.util.List;

public interface ItemValidador {
    void validar(List<InventarioItemRequest> itensNegociante, List<InventarioItemRequest> itensRecebedor);

    void validar(List<InventarioItem> itensRebelde, List<InventarioItem> itensRecebedor, List<InventarioItemRequest> itensNegociacao);
}

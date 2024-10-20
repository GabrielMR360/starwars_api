package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import java.util.List;

public record InventarioItemRequest(ItemRequest item, Integer quantidade) {
}

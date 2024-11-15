package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record InventarioItemRequest(ItemRequest item, Integer quantidade) {
}

package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record NegociacaoRequest(
        Long idNegociante,
        Long idRecebedor,
        List<InventarioItemRequest> itensNegociante,
        List<InventarioItemRequest> itensRecebedor) {
}

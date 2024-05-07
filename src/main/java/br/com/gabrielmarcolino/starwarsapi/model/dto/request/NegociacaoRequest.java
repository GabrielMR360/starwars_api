package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import java.util.List;

public record NegociacaoRequest(Long idNegociante, Long idRecebedor, List<ItemRequest> itensNegociante, List<ItemRequest> itensRecebedor) {
}

package br.com.gabrielmarcolino.starwarsapi.validation.item;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarValorItemNegociacao implements ItemValidador {
    @Override
    public void validar(List<InventarioItemRequest> itensNegociante, List<InventarioItemRequest> itensRecebedor) {
        Integer pontosNegociante = calcularPontos(itensNegociante);
        Integer pontosRecebedor = calcularPontos(itensRecebedor);

        if (pontosNegociante.compareTo(pontosRecebedor) != 0) {
            throw new BaseException(ErroEnum.PONTOS_NEGOCIACAO_INVALIDOS);
        }
    }

    private Integer calcularPontos(List<InventarioItemRequest> itens) {
        return itens.stream()
                .map(inventarioItem -> inventarioItem.quantidade() * inventarioItem.item().getPontos())
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public void validar(List<InventarioItem> itensRebelde, List<InventarioItem> itensRecebedor, List<InventarioItemRequest> itensNegociacao) {

    }
}

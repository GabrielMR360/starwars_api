package br.com.gabrielmarcolino.starwarsapi.validation.item;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarItem implements ItemValidador {
    @Override
    public void validar(List<InventarioItem> itensRebelde, List<InventarioItem> itensRecebedor, List<InventarioItemRequest> itensNegociacao) {
        itensNegociacao.stream()
                .flatMap(itensNegociante -> itensRebelde.stream()
                        .filter(itemRebeldeRecebedor -> itensNegociante.item().getNome().equals(itemRebeldeRecebedor.getItem().getNome()))
                        .peek(itemRebeldeRecebedor -> {
                            if (itensNegociante.quantidade() > itemRebeldeRecebedor.getQuantidadeItem()) {
                                throw new BaseException(ErroEnum.QUANTIDADE_ITENS_INVALIDOS);
                            }
                        }))
                .findAny()
                .orElseThrow(() -> new BaseException(ErroEnum.ITEM_NAO_ENCONTRADO));
    }

    @Override
    public void validar(List<InventarioItemRequest> itensNegociante, List<InventarioItemRequest> itensRecebedor) {

    }
}

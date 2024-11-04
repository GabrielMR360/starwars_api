package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.ItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.NegociacaoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {
    public void trocarItens(List<InventarioItem> itensRebeldeNegociante, List<InventarioItem> itensRebeldeRecebedor, NegociacaoRequest negociacaoRequest) {
        List<InventarioItemRequest> itensNegociante = negociacaoRequest.itensNegociante();
        List<InventarioItemRequest> itensRecebedor = negociacaoRequest.itensRecebedor();

        itensNegociante.forEach(item -> {
            removerItens(itensRebeldeNegociante, item.item(), item.quantidade());
            adicionarItens(itensRebeldeRecebedor, item.item(), item.quantidade());
        });

        itensRecebedor.forEach(item -> {
            removerItens(itensRebeldeRecebedor, item.item(), item.quantidade());
            adicionarItens(itensRebeldeNegociante, item.item(), item.quantidade());
        });
    }

    private void adicionarItens(List<InventarioItem> inventario, ItemRequest item, Integer quantidade) {
        inventario.forEach(inventarioItem -> {
            if (inventarioItem.getItem().getNome().equals(item.getNome())) {
                inventarioItem.setQuantidadeItem(inventarioItem.getQuantidadeItem() + quantidade);
            }
        });
    }

    private static void removerItens(List<InventarioItem> inventario, ItemRequest item, Integer quantidade) {
        inventario.forEach(inventarioItem -> {
            if (inventarioItem.getItem().getNome().equals(item.getNome())) {
                inventarioItem.setQuantidadeItem(inventarioItem.getQuantidadeItem() - quantidade);
            }
        });
    }
}

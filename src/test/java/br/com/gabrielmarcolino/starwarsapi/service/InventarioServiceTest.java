package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.Item;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.ItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.NegociacaoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventarioServiceTest {
    @InjectMocks
    private InventarioService inventarioService;

    private List<InventarioItem> itensRebeldeNegociante;

    private List<InventarioItem> itensRebeldeRecebedor;

    private NegociacaoRequest negociacaoRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        itensRebeldeNegociante = inventarioItens();
        itensRebeldeRecebedor = inventarioItens();

        ItemRequest arma = new ItemRequest("Arma", 4);
        ItemRequest comida = new ItemRequest("Comida", 3);

        InventarioItemRequest inventarioItemRequest1 = new InventarioItemRequest(arma, 1);
        InventarioItemRequest inventarioItemRequest3 = new InventarioItemRequest(comida, 1);

        List<InventarioItemRequest> itensNegociante = new ArrayList<>();
        itensNegociante.add(inventarioItemRequest1);

        List<InventarioItemRequest> itensRecebedor = new ArrayList<>();
        itensRecebedor.add(inventarioItemRequest3);

        negociacaoRequest = new NegociacaoRequest(
                1L,
                2L,
                itensNegociante,
                itensRecebedor
        );
    }

    private List<InventarioItem> inventarioItens() {
        return gerarItens().stream()
                .map(item -> new InventarioItem(null, null, item, 0))
                .toList();
    }

    private List<Item> gerarItens() {
        Item arma = new Item(null, "Arma", 4);
        Item comida = new Item(null, "Comida", 1);

        return Arrays.asList(arma, comida);
    }

    @Test
    public void deveTrocarOsItensSolicitadosNaNegociacaoEAlterarQuantidade() {
        inventarioService.trocarItens(itensRebeldeNegociante, itensRebeldeRecebedor, negociacaoRequest);

        assertEquals(0, itensRebeldeNegociante.get(0).getQuantidadeItem());
        assertEquals(2, itensRebeldeNegociante.get(1).getQuantidadeItem());

        assertEquals(2, itensRebeldeRecebedor.get(0).getQuantidadeItem());
        assertEquals(0, itensRebeldeRecebedor.get(1).getQuantidadeItem());
    }

    @Test
    public void deveLancarExcecaoQuandoNaoTemQuantidadeDeItensSolicitada() {
        ItemRequest itemRequest = new ItemRequest("Arma", 4);
        InventarioItemRequest inventarioItemRequest = new InventarioItemRequest(itemRequest, 2);
        List<InventarioItemRequest> itensNegociante = new ArrayList<>();
        itensNegociante.add(inventarioItemRequest);

        NegociacaoRequest negociacaoRequest = new NegociacaoRequest(
                1L,
                2L,
                itensNegociante,
                new ArrayList<>()
        );

        BaseException baseException = assertThrows(BaseException.class, () -> inventarioService.trocarItens(itensRebeldeNegociante, new ArrayList<>(), negociacaoRequest));
        assertEquals(ErroEnum.QUANTIDADE_ITENS_NEGATIVA, baseException.getErroEnum());
    }
}

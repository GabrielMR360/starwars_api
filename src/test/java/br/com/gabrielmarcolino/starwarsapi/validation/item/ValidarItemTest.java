package br.com.gabrielmarcolino.starwarsapi.validation.item;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.Item;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.ItemRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ValidarItemTest {
    @InjectMocks
    private ValidarItem validarItem;

    @Mock
    private InventarioItem inventarioItem;

    @Mock
    private InventarioItemRequest inventarioItemRequest;

    @Mock
    private Item item;

    @Mock
    private ItemRequest itemRequest;

    private List<InventarioItem> itensRebelde;

    private List<InventarioItemRequest> itensNegociacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        itensRebelde = List.of(inventarioItem);
        itensNegociacao = List.of(inventarioItemRequest);
        when(inventarioItem.getItem()).thenReturn(item);
        when(inventarioItemRequest.item()).thenReturn(itemRequest);
    }

    @Test
    void deveLancarExcecaoCasoRebeldeNaoTenhaQuatidadeSuficienteDeItensParaNegociar() {
        when(inventarioItem.getItem().getNome()).thenReturn("Arma");
        when(inventarioItemRequest.item().getNome()).thenReturn("Arma");
        when(inventarioItem.getQuantidadeItem()).thenReturn(1);
        when(inventarioItemRequest.quantidade()).thenReturn(2);

        BaseException baseException = assertThrows(BaseException.class, () -> validarItem.validar(itensRebelde, itensRebelde, itensNegociacao));
        assertEquals(ErroEnum.QUANTIDADE_ITENS_INVALIDOS, baseException.getErroEnum());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarItemSolicitadoNaNegociacao() {
        when(inventarioItem.getItem().getNome()).thenReturn("Arma");
        when(inventarioItemRequest.item().getNome()).thenReturn("Comida");

        BaseException baseException = assertThrows(BaseException.class, () -> validarItem.validar(itensRebelde, itensRebelde, itensNegociacao));
        assertEquals(ErroEnum.ITEM_NAO_ENCONTRADO, baseException.getErroEnum());
    }

    @Test
    void sucessoAoValidarItensParaNegociacao() {
        when(inventarioItem.getItem().getNome()).thenReturn("Arma");
        when(inventarioItemRequest.item().getNome()).thenReturn("Arma");
        when(inventarioItem.getQuantidadeItem()).thenReturn(2);
        when(inventarioItemRequest.quantidade()).thenReturn(2);

        validarItem.validar(itensRebelde, itensRebelde, itensNegociacao);
    }
}

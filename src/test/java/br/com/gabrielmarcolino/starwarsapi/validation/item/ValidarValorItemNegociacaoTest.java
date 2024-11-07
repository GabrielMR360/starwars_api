package br.com.gabrielmarcolino.starwarsapi.validation.item;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
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

public class ValidarValorItemNegociacaoTest {
    @InjectMocks
    private ValidarValorItemNegociacao validarValorItemNegociacao;

    @Mock
    private InventarioItemRequest inventarioItemNegocianteRequest;

    @Mock
    private InventarioItemRequest inventarioItemRecebedorRequest;

    @Mock
    private ItemRequest itemNegociante;

    @Mock
    private ItemRequest itemRecebedor;

    private List<InventarioItemRequest> itensNegociante;

    private List<InventarioItemRequest> itensRecebedor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        itensNegociante = List.of(inventarioItemNegocianteRequest);
        itensRecebedor = List.of(inventarioItemRecebedorRequest);
        when(inventarioItemNegocianteRequest.item()).thenReturn(itemNegociante);
        when(inventarioItemRecebedorRequest.item()).thenReturn(itemRecebedor);
    }

    @Test
    void deveLancarExcecaoCasoOsPontosNegociacaoSejamInvalidos() {
        when(inventarioItemNegocianteRequest.quantidade()).thenReturn(1);
        when(inventarioItemNegocianteRequest.item().getPontos()).thenReturn(4);

        when(inventarioItemRecebedorRequest.quantidade()).thenReturn(1);
        when(inventarioItemRecebedorRequest.item().getPontos()).thenReturn(1);

        BaseException baseException = assertThrows(BaseException.class, () -> validarValorItemNegociacao.validar(itensNegociante, itensRecebedor));
        assertEquals(ErroEnum.PONTOS_NEGOCIACAO_INVALIDOS, baseException.getErroEnum());
    }

    @Test
    void sucessoAoValidarPontosNegociacao() {
        when(inventarioItemNegocianteRequest.quantidade()).thenReturn(1);
        when(inventarioItemNegocianteRequest.item().getPontos()).thenReturn(4);

        when(inventarioItemRecebedorRequest.quantidade()).thenReturn(4);
        when(inventarioItemRecebedorRequest.item().getPontos()).thenReturn(1);

        validarValorItemNegociacao.validar(itensNegociante, itensRecebedor);
    }
}

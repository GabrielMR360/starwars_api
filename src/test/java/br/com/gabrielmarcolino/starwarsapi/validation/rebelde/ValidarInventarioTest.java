package br.com.gabrielmarcolino.starwarsapi.validation.rebelde;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.model.Inventario;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ValidarInventarioTest {
    @InjectMocks
    private ValidarInventario validarInventario;

    @Mock
    private Rebelde rebeldeNegociante;

    @Mock
    private Rebelde rebeldeRecebedor;

    @Mock
    private Inventario inventarioNegociante;

    @Mock
    private Inventario inventarioRecebedor;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecaoQuandoInventarioRebeldeNegocianteEstiverVazio() {
        when(rebeldeNegociante.getInventario()).thenReturn(inventarioNegociante);
        when(inventarioNegociante.getItens()).thenReturn(Collections.emptyList());

        assertThrows(BaseException.class, () -> validarInventario.validar(rebeldeNegociante, rebeldeRecebedor));
    }

    @Test
    void deveLancarExcecaoQuandoInventarioRebeldeRecebedorEstiverVazio() {
        when(rebeldeNegociante.getInventario()).thenReturn(inventarioNegociante);
        when(inventarioNegociante.getItens()).thenReturn(List.of(new InventarioItem()));
        when(rebeldeRecebedor.getInventario()).thenReturn(inventarioRecebedor);
        when(inventarioRecebedor.getItens()).thenReturn(Collections.emptyList());

        assertThrows(BaseException.class, () -> validarInventario.validar(rebeldeNegociante, rebeldeRecebedor));
    }

    @Test
    void naoDeveLancarExcecaoQuandoOsDoisRebeldesPossuiremItensNoInventario() {
        when(rebeldeNegociante.getInventario()).thenReturn(inventarioNegociante);
        when(inventarioNegociante.getItens()).thenReturn(List.of(new InventarioItem()));
        when(rebeldeRecebedor.getInventario()).thenReturn(inventarioRecebedor);
        when(inventarioRecebedor.getItens()).thenReturn(List.of(new InventarioItem()));

        validarInventario.validar(rebeldeNegociante, rebeldeRecebedor);
    }
}

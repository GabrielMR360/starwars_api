package br.com.gabrielmarcolino.starwarsapi.validation.rebelde;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ValidarTraidorTest {
    @InjectMocks
    private ValidarTraidor validarTraidor;

    @Mock
    private Rebelde rebeldeNegociante;

    @Mock
    private Rebelde rebeldeRecebedor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecaoQuandoRebeldeNegocianteForTraidor() {
        when(rebeldeNegociante.isTraidor()).thenReturn(true);

        BaseException baseException = assertThrows(BaseException.class, () -> validarTraidor.validar(rebeldeNegociante, rebeldeRecebedor));
        assertEquals(ErroEnum.REBELDE_TRAIDOR, baseException.getErroEnum());
    }

    @Test
    void deveLancarExcecaoQuandoRebeldeRecebedorForTraidor() {
        when(rebeldeNegociante.isTraidor()).thenReturn(false);
        when(rebeldeRecebedor.isTraidor()).thenReturn(true);

        BaseException baseException = assertThrows(BaseException.class, () -> validarTraidor.validar(rebeldeNegociante, rebeldeRecebedor));
        assertEquals(ErroEnum.REBELDE_TRAIDOR, baseException.getErroEnum());
    }

    @Test
    void deveLancarExcecaoQaundoOsDoisRebeldesForemTraidores() {
        when(rebeldeNegociante.isTraidor()).thenReturn(true);
        when(rebeldeRecebedor.isTraidor()).thenReturn(true);

        BaseException baseException = assertThrows(BaseException.class, () -> validarTraidor.validar(rebeldeNegociante, rebeldeRecebedor));
        assertEquals(ErroEnum.REBELDE_TRAIDOR, baseException.getErroEnum());
    }

    @Test
    void naoDeveLancarExcecaoQuandoOsDoisRebeldesNaoForemTraidores() {
        when(rebeldeNegociante.isTraidor()).thenReturn(false);
        when(rebeldeRecebedor.isTraidor()).thenReturn(false);

        validarTraidor.validar(rebeldeNegociante, rebeldeRecebedor);
    }
}

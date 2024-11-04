package br.com.gabrielmarcolino.starwarsapi.validation.rebelde;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import org.springframework.stereotype.Component;

@Component
public class ValidarInventario implements RebeldeValidador {
    @Override
    public void validar(Rebelde rebeldeNegociante, Rebelde rebeldeRecebedor) {
        if (rebeldeNegociante.getInventario().getItens().isEmpty() || rebeldeRecebedor.getInventario().getItens().isEmpty()) {
            throw new BaseException(ErroEnum.INVENTARIO_VAZIO);
        }
    }
}

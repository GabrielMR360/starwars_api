package br.com.gabrielmarcolino.starwarsapi.validation.rebelde;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import org.springframework.stereotype.Component;

@Component
public class ValidarTraidor implements RebeldeValidador {
    @Override
    public void validar(Rebelde rebeldeNegociante, Rebelde rebeldeRecebedor) {
        if (rebeldeNegociante.isTraidor() || rebeldeRecebedor.isTraidor()) {
            throw new BaseException(ErroEnum.REBELDE_TRAIDOR);
        }
    }
}

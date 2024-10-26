package br.com.gabrielmarcolino.starwarsapi.exception;

import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private final ErroEnum erroEnum;

    public BaseException(ErroEnum message) {
        super(message.getMensagem());
        this.erroEnum = message;
    }
}

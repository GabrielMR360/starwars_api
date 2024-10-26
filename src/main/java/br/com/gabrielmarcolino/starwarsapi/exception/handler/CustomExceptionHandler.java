package br.com.gabrielmarcolino.starwarsapi.exception.handler;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class CustomExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> globalHandlerException(BaseException baseException) {
        return new ResponseEntity<>(messageDetails(baseException), baseException.getErroEnum().getHttpStatus());
    }

    private MessageExceptionDetails messageDetails(BaseException baseException) {
        String mensagem = messageSource.getMessage(
                baseException.getErroEnum().getMensagem(),
                null,
                Locale.getDefault()
        );

        return MessageExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(baseException.getErroEnum().getCodigo())
                .error(baseException.getErroEnum().getHttpStatus().getReasonPhrase())
                .message(mensagem)
                .build();
    }
}

package br.com.gabrielmarcolino.starwarsapi.exception.handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageExceptionDetails {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}

package br.com.gabrielmarcolino.starwarsapi.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErroEnum {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "error.internalServerError"),
    PONTOS_NEGOCIACAO_INVALIDOS(HttpStatus.BAD_REQUEST, 400, "error.pontosNegociacaoInvalidos"),
    QUANTIDADE_ITENS_INVALIDOS(HttpStatus.BAD_REQUEST, 400, "error.quantidadeItensInvalidos"),
    REBELDE_TRAIDOR(HttpStatus.BAD_REQUEST, 400, "error.rebeldeTraidor"),
    INVENTARIO_VAZIO(HttpStatus.BAD_REQUEST, 400, "error.inventarioVazio"),
    ITEM_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, 404, "error.itemNaoEncontrado"),
    ;

    private final HttpStatus httpStatus;
    private final Integer codigo;
    private final String mensagem;
}

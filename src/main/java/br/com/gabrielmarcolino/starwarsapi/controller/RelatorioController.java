package br.com.gabrielmarcolino.starwarsapi.controller;

import br.com.gabrielmarcolino.starwarsapi.model.dto.response.MediaRecursosResponse;
import br.com.gabrielmarcolino.starwarsapi.service.RelatorioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorio")
@RequiredArgsConstructor
@Tag(name = "RelatorioController", description = "Operações relacionadas aos relatórios")
public class RelatorioController {
    private final RelatorioService relatorioService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Porcentagem de traidores obtida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/traidores")
    public ResponseEntity<String> getPorcentageTraidores() {
        String porcentagemTraidores = relatorioService.getPorcentagemTraidores();
        return ResponseEntity.ok(porcentagemTraidores);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Porcentagem de rebeldes obtida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/rebeldes")
    public ResponseEntity<String> getPorcentagemRebeldes() {
        String porcentagemRebeldes = relatorioService.getPorcentagemRebeldes();
        return ResponseEntity.ok(porcentagemRebeldes);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidade de recursos por rebelde obtida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/media-recursos")
    public ResponseEntity<List<MediaRecursosResponse>> getQuantidadeMediaRecursosPorTipo() {
        List<MediaRecursosResponse> mediaRecursos = relatorioService.getQuantidadeMediaRecursosPorTipo();
        return ResponseEntity.ok(mediaRecursos);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pontos perdidos devido a traidores obtidos com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/traidores/pontos-perdidos")
    public ResponseEntity<Long> getPontosPerdidosDevidoTraidores() {
        Long pontosPerdidos = relatorioService.getPontosPerdidosDevidoTraidores();
        return ResponseEntity.ok(pontosPerdidos);
    }
}

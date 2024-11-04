package br.com.gabrielmarcolino.starwarsapi.controller;

import br.com.gabrielmarcolino.starwarsapi.model.dto.response.MediaRecursosResponse;
import br.com.gabrielmarcolino.starwarsapi.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping("/traidores")
    public ResponseEntity<String> getPorcentageTraidores() {
        String porcentagemTraidores = relatorioService.getPorcentagemTraidores();
        return ResponseEntity.ok(porcentagemTraidores);
    }

    @GetMapping("/rebeldes")
    public ResponseEntity<String> getPorcentagemRebeldes() {
        String porcentagemRebeldes = relatorioService.getPorcentagemRebeldes();
        return ResponseEntity.ok(porcentagemRebeldes);
    }

    @GetMapping("/media-recursos")
    public ResponseEntity<List<MediaRecursosResponse>> getQuantidadeMediaRecursosPorTipo() {
        List<MediaRecursosResponse> mediaRecursos = relatorioService.getQuantidadeMediaRecursosPorTipo();
        return ResponseEntity.ok(mediaRecursos);
    }

    @GetMapping("/traidores/pontos-perdidos")
    public ResponseEntity<Long> getPontosPerdidosDevidoTraidores() {
        Long pontosPerdidos = relatorioService.getPontosPerdidosDevidoTraidores();
        return ResponseEntity.ok(pontosPerdidos);
    }
}

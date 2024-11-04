package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.response.MediaRecursosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RebeldeService rebeldeService;

    public String getPorcentagemTraidores() {
        List<Rebelde> rebeldes = rebeldeService.findAll();
        List<Rebelde> rebeldesTraidores = rebeldes.stream().filter(Rebelde::isTraidor).toList();

        Double quantidadeRebeldes = (double) rebeldes.size();
        Double quantidadeRebeldesTraidores = (double) rebeldesTraidores.size();
        Double porcentagemTraidores = (quantidadeRebeldesTraidores / quantidadeRebeldes) * 100.0;

        return String.format("%.0f%%", porcentagemTraidores);
    }

    public String getPorcentagemRebeldes() {
        List<Rebelde> rebeldes = rebeldeService.findAll();
        List<Rebelde> rebeldesNaoTraidores = rebeldes.stream().filter(rebelde -> !rebelde.isTraidor()).toList();

        Double totalRebeldes = (double) rebeldes.size();
        Double totalRebeldesNaoTraidores = (double) rebeldesNaoTraidores.size();
        Double porcentatemRebeldes = (totalRebeldesNaoTraidores / totalRebeldes) * 100;

        return String.format("%.0f%%", porcentatemRebeldes);
    }

    public List<MediaRecursosResponse> getQuantidadeMediaRecursosPorTipo() {
        List<Rebelde> rebeldes = rebeldeService.findAll();
        List<Rebelde> rebeldesNaoTraidores = rebeldes.stream().filter(rebelde -> !rebelde.isTraidor()).toList();
        int totalRebeldesNaoTraidores = rebeldesNaoTraidores.size();
        Map<String, Integer> totalItensPorTipo = new HashMap<>();

        rebeldesNaoTraidores.stream()
                .flatMap(rebelde -> rebelde.getInventario().getItens().stream())
                .forEach(inventarioItem -> totalItensPorTipo.merge(inventarioItem.getItem().getNome(), inventarioItem.getQuantidadeItem(), Integer::sum));

        return totalItensPorTipo.entrySet().stream()
                .map(tipo -> {
                    MediaRecursosResponse mediaRecursosResponse = new MediaRecursosResponse();
                    mediaRecursosResponse.setNome(tipo.getKey());
                    mediaRecursosResponse.setMedia((double) tipo.getValue() / totalRebeldesNaoTraidores);
                    return mediaRecursosResponse;
                })
                .toList();
    }

    public Long getPontosPerdidosDevidoTraidores() {
        List<Rebelde> rebeldes = rebeldeService.findAll();
        List<Rebelde> rebeldesTraidores = rebeldes.stream().filter(Rebelde::isTraidor).toList();

        return rebeldesTraidores.stream()
                .flatMap(rebelde -> rebelde.getInventario().getItens().stream())
                .mapToLong(inventarioItem -> (long) inventarioItem.getQuantidadeItem() * inventarioItem.getItem().getPontos())
                .sum();
    }
}
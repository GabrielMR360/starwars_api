package br.com.gabrielmarcolino.starwarsapi.controller;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.LocalizacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.NegociacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.RebeldeRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.response.LocalizacaoResponse;
import br.com.gabrielmarcolino.starwarsapi.model.dto.response.RebeldeResponse;
import br.com.gabrielmarcolino.starwarsapi.service.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rebelde")
@RequiredArgsConstructor
public class RebeldeController {
    private final RebeldeService rebeldeService;

    @PostMapping
    public ResponseEntity<RebeldeResponse> salvar(@RequestBody RebeldeRequest rebeldeRequest) {
        Rebelde rebelde = rebeldeService.salvar(rebeldeRequest);
        return ResponseEntity.ok(RebeldeResponse.toRebeldeResponse(rebelde));
    }

    @PutMapping("/reportar/{id}")
    public ResponseEntity<Void> reportarTraidor(@PathVariable Long id) {
        rebeldeService.reportarTraidor(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/localizacao/atualizar/{id}")
    public ResponseEntity<LocalizacaoResponse> atualizarLocalizacao(@PathVariable Long id, @RequestBody LocalizacaoRequest localizacaoRequest) {
        Rebelde rebelde = rebeldeService.atualizarLocalizacao(id, localizacaoRequest);
        Localizacao localizacao = rebelde.getLocalizacao();
        return ResponseEntity.ok(LocalizacaoResponse.toLocalizacaoResponse(localizacao));
    }

    @PutMapping("/negociar")
    public ResponseEntity<Void> negociarItens(@RequestBody NegociacaoRequest negociacaoRequest) {
        rebeldeService.negociarItens(negociacaoRequest);
        return ResponseEntity.ok().build();
    }
}

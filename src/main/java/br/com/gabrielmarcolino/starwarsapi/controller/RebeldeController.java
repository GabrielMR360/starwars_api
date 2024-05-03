package br.com.gabrielmarcolino.starwarsapi.controller;

import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.RebeldeRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.response.RebeldeResponse;
import br.com.gabrielmarcolino.starwarsapi.service.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

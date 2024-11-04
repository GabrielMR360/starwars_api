package br.com.gabrielmarcolino.starwarsapi.controller;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.LocalizacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.NegociacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.RebeldeRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.response.LocalizacaoResponse;
import br.com.gabrielmarcolino.starwarsapi.model.dto.response.RebeldeResponse;
import br.com.gabrielmarcolino.starwarsapi.service.RebeldeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rebelde")
@RequiredArgsConstructor
@Tag(name = "RebeldeController", description = "Operações relacionadas aos rebelde")
public class RebeldeController {
    private final RebeldeService rebeldeService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rebelde salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar rebelde"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<RebeldeResponse> salvar(@RequestBody RebeldeRequest rebeldeRequest) {
        Rebelde rebelde = rebeldeService.salvar(rebeldeRequest);
        return ResponseEntity.ok(RebeldeResponse.toRebeldeResponse(rebelde));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rebelde reportado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao reportar rebelde"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/reportar/{id}")
    public ResponseEntity<Void> reportarTraidor(@PathVariable Long id) {
        rebeldeService.reportarTraidor(id);

        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Localização atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar localização"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/localizacao/atualizar/{id}")
    public ResponseEntity<LocalizacaoResponse> atualizarLocalizacao(@PathVariable Long id, @RequestBody LocalizacaoRequest localizacaoRequest) {
        Rebelde rebelde = rebeldeService.atualizarLocalizacao(id, localizacaoRequest);
        Localizacao localizacao = rebelde.getLocalizacao();
        return ResponseEntity.ok(LocalizacaoResponse.toLocalizacaoResponse(localizacao));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negociação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar negociação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/negociar")
    public ResponseEntity<Void> negociarItens(@RequestBody NegociacaoRequest negociacaoRequest) {
        rebeldeService.negociarItens(negociacaoRequest);
        return ResponseEntity.ok().build();
    }
}

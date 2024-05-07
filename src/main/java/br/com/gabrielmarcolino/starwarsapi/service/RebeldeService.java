package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.LocalizacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.RebeldeRequest;
import br.com.gabrielmarcolino.starwarsapi.repository.RebeldeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebeldeService {
    private final RebeldeRepository rebeldeRepository;

    public Rebelde salvar(RebeldeRequest rebeldeRequest) {
        return rebeldeRepository.save(rebeldeRequest.toRebelde());
    }

    public Rebelde buscarPorId(Long id) {
        return rebeldeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID " + id + " não encontrado."));
    }

    public void reportarTraidor(Long id) {
        Rebelde rebelde = buscarPorId(id);

        rebelde.setQuantidadeReports(rebelde.getQuantidadeReports() + 1);
        if (rebelde.getQuantidadeReports() >= 3) {
            rebelde.setTraidor(true);
        }

        rebeldeRepository.save(rebelde);
    }

    public Rebelde atualizarLocalizacao(Long id, LocalizacaoRequest localizacaoRequest) {
        Rebelde rebelde = buscarPorId(id);
        Localizacao localizacao = rebelde.getLocalizacao();
        Long localizacaoId = localizacao.getId();

        localizacao.setLatitude(localizacaoRequest.latitude());
        localizacao.setLongitude(localizacaoRequest.longitude());
        localizacao.setNome(localizacaoRequest.nome());
        localizacao.setId(localizacaoId);

        rebelde.setLocalizacao(localizacao);
        return rebeldeRepository.save(rebelde);
    }
}

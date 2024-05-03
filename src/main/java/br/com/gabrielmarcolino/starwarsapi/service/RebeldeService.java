package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
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

    public void reportarTraidor(Long id) {
        Rebelde rebelde = rebeldeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID " + id + " não encontrado."));

        rebelde.setQuantidadeReports(rebelde.getQuantidadeReports() + 1);
        if (rebelde.getQuantidadeReports() >= 3) {
            rebelde.setTraidor(true);
        }

        rebeldeRepository.save(rebelde);
    }
}

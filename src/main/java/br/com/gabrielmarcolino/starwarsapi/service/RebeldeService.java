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
}

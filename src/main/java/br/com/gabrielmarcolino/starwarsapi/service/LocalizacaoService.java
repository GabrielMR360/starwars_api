package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.repository.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {
    private final LocalizacaoRepository localizacaoRepository;

}

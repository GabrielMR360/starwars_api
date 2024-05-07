package br.com.gabrielmarcolino.starwarsapi.repository;

import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
}

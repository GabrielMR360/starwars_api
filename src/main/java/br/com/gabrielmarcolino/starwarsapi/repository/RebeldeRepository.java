package br.com.gabrielmarcolino.starwarsapi.repository;

import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {
}

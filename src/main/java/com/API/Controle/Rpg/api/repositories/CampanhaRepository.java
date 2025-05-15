package com.API.Controle.Rpg.api.repositories;

import com.API.Controle.Rpg.api.domain.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
    @Override
    Optional<Campanha> findById(Long campanhaId);
    Optional<Campanha> findByNome(String nome);
    Optional<List<Campanha>> findBySistema(String sistema);

}

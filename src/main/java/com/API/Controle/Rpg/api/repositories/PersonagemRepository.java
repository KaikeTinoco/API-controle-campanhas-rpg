package com.API.Controle.Rpg.api.repositories;

import com.API.Controle.Rpg.api.domain.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Optional<Personagem> findByNome(String nome);
    Optional<Personagem> findById(Long id);
    Optional<List<Personagem>> findByIsNpc(boolean isNpc);
}

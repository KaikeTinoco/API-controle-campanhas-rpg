package com.API.Controle.Rpg.api.mapper;

import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonagemMapper {
    Personagem toEntity(PersonagemDTO dto);

    PersonagemDTO toDto(Personagem personagem);
}

package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.exceptions.BadRequestException;
import com.API.Controle.Rpg.api.exceptions.NotFoundException;
import com.API.Controle.Rpg.api.repositories.CampanhaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampanhaService {
    private final CampanhaRepository repository;

    @Autowired
    public CampanhaService(CampanhaRepository repository) {
        this.repository = repository;
    }

    public Campanha criarCampanha(CampanhaDTO dto){
        if (dto.getNome() == null || dto.getSistema() == null
        || dto.getDescricaoHistoria() == null || dto.getRestricoes() == null) {
            throw new BadRequestException("por favor, preencha todos os campos da campanha!");
        }
        Campanha campanha = Campanha.builder()
                .nome(dto.getNome())
                .sistema(dto.getSistema())
                .descricaoHistoria(dto.getDescricaoHistoria())
                .contextoNarrativoAtual(new ArrayList<>())
                .personagemList(new ArrayList<>())
                .status(StatusCampanha.ATIVA)
                .dataCriacao(LocalDateTime.now())
                .ultimaAtualizacao(LocalDateTime.now())
                .notasPublicas(new ArrayList<>())
                .notasPrivadas(new ArrayList<>())
                .logDeEventos(new ArrayList<>())
                .restricoes(new ArrayList<>())
                .build();
        repository.save(campanha);
        return campanha;
    }

    public List<Campanha> buscarTodasCampanhas(){
        return repository.findAll();
    }

    //trocar para outro comando quando houver tratação de erros!
    @Transactional
    public Campanha buscarCampanhaPorNome(String nomeCampanha){
        if(nomeCampanha.isBlank()){
            throw new BadRequestException("por favor informe um nome de campanha válido");
        }
        return repository.findByNome(nomeCampanha).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma campanha com esse nome")
        );
    }

    //trocar para outro comando quando houver tratação de erros!
    @Transactional
    public  List<Campanha> buscarCampanhaPorSistema(String sistema){
        if(sistema.isBlank()){
            throw new RuntimeException("por favor informe o nome do sistema!");
        }
        return repository.findBySistema(sistema).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma campanha com esse sistema")
        );
    }

    public String deletarCampanha(Long id){
        if(id == null){
            throw new BadRequestException("por favor informe um id válido");
        }
         Campanha campanha = repository.findById(id).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma campanha com esse sistema")
        );
         repository.delete(campanha);
         return "Deletado com sucesso!";
    }
}

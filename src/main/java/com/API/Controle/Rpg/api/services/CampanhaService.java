package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.repositories.CampanhaRepository;
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

    public void criarCampanha(CampanhaDTO dto){
        if (dto.getNome().isBlank() || dto.getSistema().isBlank()
        || dto.getDescricaoHistoria().isBlank() || dto.getRestricoes().isEmpty()) {
            throw new RuntimeException("por favor, preencha todos os campos da campanha!");
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
    }

    public List<Campanha> buscarTodasCampanhas(){
        return repository.findAll();
    }

    //trocar para outro comando quando houver tratação de erros!
    public Campanha buscarCampanhaPorNome(String nomeCampanha){
        if(repository.findByNome(nomeCampanha).isEmpty()){
            throw new RuntimeException("não foi possível encontrar uma campanha com esse nome!");
        } else{
            return repository.findByNome(nomeCampanha).get();
        }
    }

    //trocar para outro comando quando houver tratação de erros!
    public  List<Campanha> buscarCampanhaPorSistema(String sistema){
        if(sistema.isBlank()){
            throw new RuntimeException("por favor informe o nome do sistema!");
        } else if (repository.findBySistema(sistema).isEmpty()) {
            throw new RuntimeException("não foi possível encontrar uma campanha com esse sistema!");
        } else {
            return repository.findBySistema(sistema).get();
        }
    }
}

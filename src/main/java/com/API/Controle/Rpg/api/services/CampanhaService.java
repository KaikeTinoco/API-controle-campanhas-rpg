package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.repositories.CampanhaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampanhaService {
    private final CampanhaRepository repository;

    @Autowired
    public CampanhaService(CampanhaRepository repository) {
        this.repository = repository;
    }

    public Campanha criarCampanha(CampanhaDTO dto){
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
        return campanha;
    }

    public List<Campanha> buscarTodasCampanhas(){
        return repository.findAll();
    }

    //trocar para outro comando quando houver tratação de erros!
    @Transactional
    public Campanha buscarCampanhaPorNome(String nomeCampanha){
        if(repository.findByNome(nomeCampanha).isEmpty()){
            throw new RuntimeException("não foi possível encontrar uma campanha com esse nome!");
        } else{
            return repository.findByNome(nomeCampanha).get();
        }
    }

    //trocar para outro comando quando houver tratação de erros!
    @Transactional
    public  List<Campanha> buscarCampanhaPorSistema(String sistema){
        if(sistema.isBlank()){
            throw new RuntimeException("por favor informe o nome do sistema!");
        } else if (repository.findBySistema(sistema).isEmpty()) {
            throw new RuntimeException("não foi possível encontrar uma campanha com esse sistema!");
        } else {
            return repository.findBySistema(sistema).get();
        }
    }

    public String deletarCampanha(Long id){
        Optional<Campanha> campanha = repository.findById(id);

        if(campanha.isEmpty()){
            throw new RuntimeException("por favor informe o id da campanha!");
        } else{
            repository.delete(campanha.get());
            return "Campanha deletada com sucesso!";
        }
    }
}

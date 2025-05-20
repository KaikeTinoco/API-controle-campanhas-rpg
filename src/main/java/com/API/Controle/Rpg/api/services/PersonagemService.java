package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import com.API.Controle.Rpg.api.exceptions.BadRequestException;
import com.API.Controle.Rpg.api.exceptions.NotFoundException;
import com.API.Controle.Rpg.api.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {
    private final PersonagemRepository repository;
    private final CampanhaService campanhaService;
    private Campanha campanha;

    @Autowired
    public PersonagemService(PersonagemRepository repository, CampanhaService campanhaService) {
        this.repository = repository;
        this.campanhaService = campanhaService;
    }

    //essa função depois só vai receber um texto de descrição, e quem vai criar o DTO vai ser a IA
    public Personagem criarPersonagem(PersonagemDTO dto, Long campanhaId){
        Campanha c = campanhaService.findById(campanhaId);
        Personagem personagemNovo = Personagem.builder()
                .nome(dto.getNome())
                .idade(dto.getIdade())
                .sexo(dto.getSexo())
                .nivel(dto.getNivel())
                .classe(dto.getClasse())
                .subclasse(dto.getSubclasse())
                .tendencia(dto.getTendencia())
                .campanha(c)
                .forca(dto.getForca())
                .destreza(dto.getDestreza())
                .constituicao(dto.getConstituicao())
                .inteligencia(dto.getInteligencia())
                .sabedoria(dto.getSabedoria())
                .carisma(dto.getCarisma())
                .modForca(dto.getModForca())
                .modDestreza(dto.getModDestreza())
                .modConstituicao(dto.getModConstituicao())
                .modInteligencia(dto.getModInteligencia())
                .modSabedoria(dto.getModSabedoria())
                .modCarisma(dto.getModCarisma())
                .pontosDeVida(dto.getPontosDeVida())
                .dadoDeVida(dto.getDadoDeVida())
                .classeArmadura(dto.getClasseArmadura())
                .cdMagia(dto.getCdMagia())
                .modAtaqueMagico(dto.getModAtaqueMagico())
                .espacosMagiaNivelUm(dto.getEspacosMagiaNivelUm())
                .espacosMagiaNivelDois(dto.getEspacosMagiaNivelDois())
                .magiasTruques(dto.getMagiasTruques())
                .magiasNivelUm(dto.getMagiasNivelUm())
                .magiasNivelDois(dto.getMagiasNivelDois())
                .equipamentos(dto.getEquipamentos())
                .proficiencias(dto.getProficiencias())
                .idiomas(dto.getIdiomas())
                .ferramentas(dto.getFerramentas())
                .salvaguardas(dto.getSalvaguardas())
                .tracosDePersonalidade(dto.getTracosDePersonalidade())
                .ideais(dto.getIdeais())
                .vinculo(dto.getVinculo())
                .defeito(dto.getDefeito())
                .isNpc(dto.isNpc())
                .build();
        repository.save(personagemNovo);
        campanhaService.AdicionarPersonagemNaCampanha(personagemNovo, c);
        return personagemNovo;
    }

    public List<Personagem> listarPersonagensNaCampanha(String nomeCampanha){
        Campanha c = campanhaService.buscarCampanhaPorNome(nomeCampanha);
        return c.getPersonagemList();
    }

    public List<Personagem> listarPersonagensNaCampanha(Long id){
        Campanha c = campanhaService.findById(id);
        return c.getPersonagemList();
    }

    public List<Personagem> checarParametros(Long id, String campanhaNome){
        if (id == null && campanhaNome == null){
            throw new BadRequestException("por favor, informe um dos parametros: id da campanha/ nome da campanha");
        }
        if (campanhaNome == null){
            return listarPersonagensNaCampanha(id);
        } else {
            return listarPersonagensNaCampanha(campanhaNome);
        }
    }

    private Personagem acharPersonagemPorNome(String nomePersonagem, String nomeCampanha){
        Campanha campanha = campanhaService.buscarCampanhaPorNome(nomeCampanha);
        return campanha.getPersonagemList().stream()
                .filter(personagem -> personagem.getNome().equalsIgnoreCase(nomePersonagem))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Não foi possível encontrar o personagem:" + nomePersonagem));

    }

}



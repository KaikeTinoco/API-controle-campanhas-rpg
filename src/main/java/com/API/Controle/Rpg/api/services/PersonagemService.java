package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.PersonagemDTO;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.domain.model.Personagem;
import com.API.Controle.Rpg.api.exceptions.BadRequestException;
import com.API.Controle.Rpg.api.exceptions.NotFoundException;
import com.API.Controle.Rpg.api.mapper.PersonagemMapper;
import com.API.Controle.Rpg.api.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PersonagemService {
    private final PersonagemRepository repository;
    private final CampanhaService campanhaService;
    private final PersonagemMapper mapper;
    private Campanha campanha;

    @Autowired
    public PersonagemService(PersonagemRepository repository, CampanhaService campanhaService, PersonagemMapper mapper) {
        this.repository = repository;
        this.campanhaService = campanhaService;
        this.mapper = mapper;
    }



    /**
     * <p>
     * Cria e persiste um novo {@link Personagem} em uma {@link Campanha}.
     * Utiliza o {@link PersonagemMapper} para converter o DTO em entidade,
     * reduzindo o acoplamento entre as camadas de transporte e persistência.
     * </p>
     * @param dto Dados provenientes da API externa.
     * @param campanhaId Identificador da campanha de destino.
     * @return O personagem criado com seu ID gerado.
     */
    public Personagem criarPersonagem(PersonagemDTO dto, Long campanhaId) {
        Campanha c = campanhaService.findById(campanhaId);
        Personagem personagemNovo = mapper.toEntity(dto);
        repository.save(personagemNovo);
        campanhaService.adicionarPersonagemNaCampanha(personagemNovo, c);
        return personagemNovo;
    }

    /**
     * <p>
     * Recupera a lista de personagens associados a uma campanha identificada pelo nome.
     * </p>
     * @param nomeCampanha O nome da campanha para busca.
     * @return Uma {@link List} de {@link Personagem} pertencentes à campanha.
     */
    public List<Personagem> listarPersonagensNaCampanha(String nomeCampanha){
        Campanha c = campanhaService.buscarCampanhaPorNome(nomeCampanha);
        return c.getPersonagemList();
    }

    /**
     * <p>
     * Recupera a lista de personagens associados a uma campanha identificada pelo ID.
     * </p>
     * @param id O identificador único da campanha.
     * @return Uma {@link List} de {@link Personagem} pertencentes à campanha.
     */
    public List<Personagem> listarPersonagensNaCampanha(Long id){
        Campanha c = campanhaService.findById(id);
        return c.getPersonagemList();
    }

    /**
     * <p>
     * Método de conveniência que valida qual parâmetro de busca foi fornecido (ID ou Nome)
     * e redireciona para a listagem correta de personagens.
     * </p>
     * @param id O ID da campanha (pode ser nulo).
     * @param campanhaNome O nome da campanha (pode ser nulo).
     * @return Lista de personagens da campanha encontrada.
     * @throws BadRequestException Caso ambos os parâmetros sejam nulos.
     */
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

    /**
     * <p>
     * Busca um personagem específico dentro de uma campanha informada.
     * A busca pelo nome do personagem ignora letras maiúsculas ou minúsculas.
     * </p>
     * @param nomePersonagem Nome do personagem a ser localizado.
     * @param nomeCampanha Nome da campanha onde o personagem está registrado.
     * @return A entidade {@link Personagem} encontrada.
     * @throws NotFoundException Caso o personagem não exista na lista da campanha.
     */
    public Personagem acharPersonagemPorNome(String nomePersonagem, String nomeCampanha){
        Campanha campanha = campanhaService.buscarCampanhaPorNome(nomeCampanha);
        return campanha.getPersonagemList().stream()
                .filter(personagem -> personagem.getNome().equalsIgnoreCase(nomePersonagem))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Não foi possível encontrar o personagem:" + nomePersonagem));

    }

    /**
     * <p>
     * Atualiza os dados de um {@link Personagem} existente. O método garante o vínculo
     * correto com a {@link Campanha} antes de persistir as alterações.
     * </p>
     * @param nomeCampanha Nome da campanha para reatribuição de contexto.
     * @param personagemAtualizado A entidade do personagem com os dados novos.
     * @return O {@link Personagem} atualizado.
     */
    public Personagem atualizarPersonagem(String nomeCampanha, Personagem personagemAtualizado) {
        Campanha campanha = campanhaService.buscarCampanhaPorNome(nomeCampanha);
        personagemAtualizado.setCampanha(campanha);
        repository.save(personagemAtualizado);
        return personagemAtualizado;
    }


}



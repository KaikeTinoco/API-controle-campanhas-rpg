package com.API.Controle.Rpg.api.services;

import com.API.Controle.Rpg.api.domain.dtos.CampanhaDTO;
import com.API.Controle.Rpg.api.domain.dtos.ResumoDTO;
import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.API.Controle.Rpg.api.domain.model.Campanha;
import com.API.Controle.Rpg.api.domain.model.Personagem;
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

    /**
     * <p>
     *     Função que cria e salva uma entidade {@link Campanha} no banco.
     *     O DTO é enviado por <a href="https://github.com/KaikeTinoco/Bot-Gerador-de-Hist-ria.git">uma API externa</a> e então
     *     aqui ele é tratado e convertido
     * </p>
     * @param dto o DTO recebido pela API externa
     * @return Se tudo estiver preenchido, retorna a própria entidade criada.Caso contrário retorna um {@link BadRequestException}
     */
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

    /**
     * <p>
     * Recupera uma lista contendo todas as entidades {@link Campanha} armazenadas no banco de dados.
     * </p>
     * @return Uma {@link List} de todas as campanhas encontradas.
     */
    public List<Campanha> buscarTodasCampanhas(){
        return repository.findAll();
    }

    /**
     * <p>
     * Busca uma {@link Campanha} específica através do seu nome.
     * O nome é tratado para remover espaços em branco sobressalentes.
     * </p>
     * @param nomeCampanha O nome da campanha a ser pesquisada.
     * @return A entidade encontrada.
     * @throws BadRequestException Caso o nome fornecido esteja em branco.
     * @throws NotFoundException Caso nenhuma campanha seja encontrada com o nome informado.
     */
    @Transactional
    public Campanha buscarCampanhaPorNome(String nomeCampanha){
        if(nomeCampanha.isBlank()){
            throw new BadRequestException("por favor informe um nome de campanha válido");
        }
        return repository.findByNome(nomeCampanha.strip()).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma campanha com esse nome")
        );
    }

    /**
     * <p>
     * Localiza todas as campanhas vinculadas a um sistema de RPG específico.
     * </p>
     * @param sistema O nome do sistema (ex: D&D 5e, Ordem Paranormal).
     * @return Uma lista de entidades {@link Campanha}.
     * @throws RuntimeException Caso o parâmetro sistema esteja vazio.
     * @throws NotFoundException Caso não existam campanhas registradas para o sistema informado.
     */
    @Transactional
    public  List<Campanha> buscarCampanhaPorSistema(String sistema){
        if(sistema.isBlank()){
            throw new BadRequestException("por favor informe o nome do sistema!");
        }
        return repository.findBySistema(sistema).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma campanha com esse sistema")
        );
    }

    /**
     * <p>
     * Remove uma {@link Campanha} do banco de dados através do seu ID único.
     * </p>
     * @param id O identificador único da campanha.
     * @return Uma mensagem de confirmação da exclusão.
     * @throws BadRequestException Caso o ID seja nulo.
     * @throws NotFoundException Caso o ID não corresponda a nenhuma campanha no banco.
     */
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

    /**
     * <p>
     * Realiza uma busca direta pelo ID da {@link Campanha}.
     * Utilizado principalmente para operações internas de verificação.
     * </p>
     * @param id O identificador único.
     * @return A entidade {@link Campanha} correspondente.
     * @throws BadRequestException Caso o ID informado seja nulo.
     * @throws NotFoundException Caso o registro não exista.
     */
    public Campanha findById(Long id){
        if (id == null){
            throw new  BadRequestException("por favor informe um id válido");
        }
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma campanha com id" + id));
    }

    /**
     * <p>
     * Associa um novo {@link Personagem} a uma {@link Campanha} já existente e persiste a alteração.
     * </p>
     * @param personagemNovo A instância do personagem a ser adicionado.
     * @param campanha A instância da campanha que receberá o personagem.
     */
    public void adicionarPersonagemNaCampanha(Personagem personagemNovo, Campanha campanha){
        campanha.adicionarPersonagem(personagemNovo);
        repository.save(campanha);
    }

    /**
     * <p>
     * Persiste o estado atual de uma entidade {@link Campanha} no banco de dados.
     * </p>
     * @param campanha A entidade a ser salva.
     */
    public void salvarCampanha(Campanha campanha){
        repository.save(campanha);
    }

    /**
     * <p>
     * Atualiza o contexto narrativo de uma campanha com base no resumo de uma sessão.
     * Este método localiza a campanha pelo nome e anexa as novas informações ao histórico.
     * </p>
     * @param resumoSessao DTO contendo o nome da campanha e o texto do resumo.
     * @return Mensagem de sucesso após a persistência.
     * @throws BadRequestException Caso campos obrigatórios do DTO sejam nulos.
     */
    public String salvarSessao(ResumoDTO resumoSessao) {
        if (resumoSessao.getResumoSessao() == null || resumoSessao.getNomeCampanha() == null){
            throw new BadRequestException("por favor, informe o nome da Campanha e o resumo da sessão");
        }
        Campanha campanha = buscarCampanhaPorNome(resumoSessao.getNomeCampanha());
        campanha.atualizarContextoAtual(resumoSessao.getResumoSessao());
        salvarCampanha(campanha);
        return "Campanha atualizada com sucesso";
    }
}

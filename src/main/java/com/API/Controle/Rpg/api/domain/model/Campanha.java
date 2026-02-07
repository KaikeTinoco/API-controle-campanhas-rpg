package com.API.Controle.Rpg.api.domain.model;

import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode()
/**
 * Entidade Principal da API.
 * Estabelece uma relação com {@link Personagem} de OneToMany, sendo essa a parte mãe
 * responsável por receber personagens e todos os parâmetros necessários para uma campanha de D&D
 *
 * @author Kaike Tinoco Nantes
 * @see Personagem
 */
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sistema;
    @Lob
    private String descricaoHistoria;

    @ElementCollection
    @Lob
    private List<String> contextoNarrativoAtual;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Personagem> personagemList;
    @Enumerated(EnumType.STRING)
    private StatusCampanha status;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime ultimaAtualizacao;
    @ElementCollection
    private List<String> notasPublicas;
    @ElementCollection
    private List<String> notasPrivadas;
    @ElementCollection
    private List<String> logDeEventos;
    @ElementCollection
    private List<String> restricoes;

    /**
     *<p>
     *     Basicamente a função adiciona um personagem à campanha
     *
     *</p>
     * @param p o personagem que será adicionado
     */
    public void adicionarPersonagem(Personagem p) {
        p.setCampanha(this);
        this.personagemList.add(p);
    }

    /**
     * <p>
     *     Atualiza a campanha, salvando o que aconteceu na última sessão
     * </p>
     * @param descricao descrição geral do que ocorreu na última sessão
     */
    public void atualizarContextoAtual(String descricao){
        this.contextoNarrativoAtual.add(descricao);
    }

    /**
     * <p>Atualiza as anotações que o bot pode compartilhar com os jogadores</p>
     * @param nota dados que podem ser compartilhados com os jogadores
     */
    public void atualizarNotasPublicas(String nota){
        this.notasPublicas.add(nota);
    }

    /**
     * <p>Atualiza as anotações que o bot não pode compartilhar com os jogadores</p>
     * @param nota dados que não podem ser compartilhados com os jogadores
     */
    public void atualizarNotasPrivadas(String nota){
        this.notasPrivadas.add(nota);
    }

    /**
     * Atualiza os últimos eventos importantes da campanha
     * @param evento algo importante para a narrativa da história
     */
    public void atualizarEventos(String evento){
        this.logDeEventos.add(evento);
    }



}
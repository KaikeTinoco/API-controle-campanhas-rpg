package com.API.Controle.Rpg.api.domain.model;

import com.API.Controle.Rpg.api.domain.enums.StatusCampanha;
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

    public void adicionarPersonagem(Personagem p) {
        p.setCampanha(this);
        this.personagemList.add(p);
    }

    public void atualizarContextoAtual(String descricao){
        this.contextoNarrativoAtual.add(descricao);
    }

    public void atualizarNotasPublicas(String nota){
        this.notasPublicas.add(nota);
    }

    public void atualizarNotasPrivadas(String nota){
        this.notasPrivadas.add(nota);
    }

    public void atualizarEventos(String evento){
        this.logDeEventos.add(evento);
    }



}
package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "pessoas_evento")
public class PessoasEventoEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "presenca")
    private Boolean presenca;

    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Column(name = "id_evento")
    private Long idEvento;

    @ManyToMany
    @JoinTable(
            name = "pessoas_evento",
            joinColumns = @JoinColumn(name = "id_pessoa", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id")
    )
    private List<PessoasEventoEntity> pessoasEvento;
}

package com.entra21.voluntariosApp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @JsonIgnore
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    @JsonIgnore
    private EventoEntity evento;
}

package com.entra21.voluntariosApp.model.entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 0d6e9a3340aaf836c0ec1f7d7453763599b3c8b8
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contribuicao")
public class ContribuicaoEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_pessoa",referencedColumnName = "id")
    @JsonIgnore
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_organizacao", referencedColumnName = "id")
    private OrganizacaoEntity organizacao;
}
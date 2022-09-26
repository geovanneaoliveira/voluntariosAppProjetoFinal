package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "organizacao")

public class OrganizacaoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_supervisor",referencedColumnName = "id")
    private PessoaEntity supervisor;
}
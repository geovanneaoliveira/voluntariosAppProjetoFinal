package com.entra21.voluntariosApp.model.entity;

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
    private double valor;

    @Column(name = "data")
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_pessoa",referencedColumnName = "id")
    private PessoaEntity idPessoa;

    @ManyToOne
    @JoinColumn(name = "id_organizacao", referencedColumnName = "id")
    private OrganizacaoEntity idOrganizacao;

}
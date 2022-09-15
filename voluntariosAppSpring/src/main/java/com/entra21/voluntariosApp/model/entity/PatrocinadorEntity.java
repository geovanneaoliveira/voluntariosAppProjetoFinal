package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patrocinador")
public class PatrocinadorEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_representante", referencedColumnName = "id")
    private PessoaEntity representante;

}

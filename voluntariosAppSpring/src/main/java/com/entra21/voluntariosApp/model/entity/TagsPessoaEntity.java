package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tag_interesse_pessoa")
public class TagsPessoaEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tag",referencedColumnName = "id")
    private TagsEntity tagsEntity;

    @ManyToOne
    @JoinColumn(name = "id_pessoa",referencedColumnName = "id")
    private PessoaEntity pessoa;
}

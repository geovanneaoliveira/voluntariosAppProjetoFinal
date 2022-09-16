package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Data
@Entity
@Table(name = "evento")
public class EventoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data")
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_organizadora",referencedColumnName = "id")
    private OrganizacaoEntity organizacao;

    @ManyToMany
    @JoinTable(
            name = "tags_evento",
            joinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_tag", referencedColumnName = "id")
    )
    private List<TagsEntity> tags;

    @ManyToMany
    @JoinTable(
            name = "patrocinadores_evento",
            joinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_patrocinador", referencedColumnName = "id")
    )
    private List<PatrocinadorEntity> patrocinadores;

    //@ManyToOne
    //@JoinColumn(name = "tags_evento", referencedColumnName = "id")
}
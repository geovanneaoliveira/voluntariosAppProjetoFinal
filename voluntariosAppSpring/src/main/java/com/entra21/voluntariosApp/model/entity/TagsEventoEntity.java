package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags_evento")
public class TagsEventoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tag",referencedColumnName = "id")
    private TagsEntity tagsEntity;

    @ManyToOne
    @JoinColumn(name = "id_evento",referencedColumnName = "id")
    private EventoEntity eventoEntity;
}

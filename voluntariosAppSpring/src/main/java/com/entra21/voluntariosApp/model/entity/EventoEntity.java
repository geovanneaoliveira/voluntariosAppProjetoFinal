package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "id_patrocinador", referencedColumnName = "id")
    private PatrocinadorEntity patrocinador;

    //@ManyToOne
    //@JoinColumn(name = "tags_evento", referencedColumnName = "id")
}
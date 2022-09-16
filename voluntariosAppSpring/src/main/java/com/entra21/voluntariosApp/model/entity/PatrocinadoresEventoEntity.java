package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "patrocinadores_evento")
public class PatrocinadoresEventoEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_patrocinador", referencedColumnName = "id")
    private PatrocinadorEntity patrocinador;

    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private EventoEntity evento;

}

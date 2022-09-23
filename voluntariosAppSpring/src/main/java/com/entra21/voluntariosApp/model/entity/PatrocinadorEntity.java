package com.entra21.voluntariosApp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

<<<<<<< Updated upstream


=======
    @ManyToMany
    @JoinTable(
            name = "patrocinadores_evento",
            joinColumns = @JoinColumn(name = "id_patrocinador", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id")
    )
    private List<EventoEntity> evento;
>>>>>>> Stashed changes
}

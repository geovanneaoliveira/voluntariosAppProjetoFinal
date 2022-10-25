package com.entra21.voluntariosApp.model.dto.user;

import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import lombok.Data;

@Data
public class PatrocinadorDTO {
    private String nome;
    private PessoaEntity representante;
    private String fotoPatrocinador;
}

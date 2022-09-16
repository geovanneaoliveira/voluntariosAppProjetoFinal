package com.entra21.voluntariosApp.model.dto;

import lombok.Data;

@Data
public class PessoasEventoDTO {
    private Long id_pessoa;
    private Long id_evento;
    private boolean presenca;
}

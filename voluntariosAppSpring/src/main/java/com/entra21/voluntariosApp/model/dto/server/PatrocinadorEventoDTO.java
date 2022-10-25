package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

import java.util.List;

@Data
public class PatrocinadorEventoDTO {
    private List<Long> idsPatrocinadores;
    private Long idEvento;
}

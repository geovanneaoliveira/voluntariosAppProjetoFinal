package com.entra21.voluntariosApp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetContribuicoesDTO {
    private LocalDateTime data;
    private Double valor;
    private String nomeUsuario;
    private String sobrenome;
    private String nomeOrg;
}

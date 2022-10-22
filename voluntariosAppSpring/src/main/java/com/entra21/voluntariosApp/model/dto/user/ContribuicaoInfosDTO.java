package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContribuicaoInfosDTO {
    private Long id;
    private LocalDateTime data;
    private Double valor;
    private String nomeUsuario;
    private String sobrenome;
    private String nomeOrg;
}

package com.entra21.voluntariosApp.model.dto;

import lombok.Data;

@Data
public class PessoaDTO {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private Boolean ativo;
}

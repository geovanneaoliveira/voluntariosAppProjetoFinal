package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

import java.util.List;

/**DTO dedicado para login na aplicação.
 * */
@Data
public class LoginDTO {
    private Long id;
    private String username;
    private String password;
    private String login;
    private String senha;
    private String nome;
    private String sobrenome;
    private String fotoPerfil;
    private String telefone;
    private String cpf;
}

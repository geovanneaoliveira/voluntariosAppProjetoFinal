package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

/**DTO dedicado para login na aplicação.
 * */
@Data
public class LoginDTO {
    private Long id;
    private String login;
    private String senha;
    private String fotoPerfil;
}

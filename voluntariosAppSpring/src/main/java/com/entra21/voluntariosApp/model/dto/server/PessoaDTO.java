package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

/**DTO dedicado para enviar informações de uma U para o servidor.<br>
 * Atributos:
 * <li>String nome</li>
 * <li>String sobrenome</li>
 * <li>String telefone</li>
 * <li>String cpf</li>
 * <li>String login</li>
 * <li>String senha</li>
 * <li>Boolean ativo </li>
 */
@Data
public class PessoaDTO {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private String caminhoImagem;
    private Boolean ativo;
}

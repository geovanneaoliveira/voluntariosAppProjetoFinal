package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.PessoaDTO;
import com.entra21.voluntariosApp.model.dto.user.LoginDTO;
import com.entra21.voluntariosApp.model.dto.user.LoginSemIdDTO;
import com.entra21.voluntariosApp.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public Object teste() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Chama um método que cadastra uma nova pessoa no banco de dados
     * @param cadastro
     */
    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody PessoaDTO cadastro) {
        pessoaService.cadastrar(cadastro);
    }

    /**
     * Chama um método que atualiza os dados de uma pessoa de acordo com o nome informado
     * @param antigoLogin
     * @param dto
     */
    @PutMapping("/atualizar")
    public void atualizar(@RequestParam(name = "antigoLogin") String antigoLogin, @RequestBody PessoaDTO dto) {
        pessoaService.atualizar(antigoLogin, dto);
    }

    /**
     * Chama um método que atualiza o status de login do usuário
     * @param login
     */
    @PutMapping("/status")
    public void status(@RequestParam(name = "login") String login) {
        pessoaService.status(login);
    }

    @PostMapping("/login")
    public LoginDTO login(@RequestBody LoginSemIdDTO login){
        return pessoaService.login(login);
    }
}

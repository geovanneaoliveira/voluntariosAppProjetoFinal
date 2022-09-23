package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.PessoaDTO;
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
    public String teste() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody PessoaDTO cadastro) {
        pessoaService.cadastrar(cadastro);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestParam(name = "antigoLogin") String antigoLogin, @RequestBody PessoaDTO dto){
        pessoaService.atualizar(antigoLogin, dto);
    }

    @PutMapping("/status")
    public void status(@RequestParam(name = "login") String login) {
        pessoaService.status(login);
    }
}

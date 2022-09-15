package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.ContribuicaoDTO;
import com.entra21.voluntariosApp.view.service.ContribuicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contribuicao")
public class ContribuicaoRestController {

    @Autowired
    private ContribuicaoService contribuicaoService;

    @PostMapping("/adicionar")
    public void addContribuicao(@RequestBody ContribuicaoDTO contribuicaoAdd) {
        contribuicaoService.addContribuicao(contribuicaoAdd);
    }

    @GetMapping
    public List<ContribuicaoDTO> getFranquias() {
        return contribuicaoService.findAllContribuicao();
    }

    @GetMapping("/{id}")
    public ContribuicaoDTO contribuicaoDTO(@PathVariable(name = "id")Long id){
        return contribuicaoService.getById(id);
    }

}
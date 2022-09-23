package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.ContribuicaoDTOs;
import com.entra21.voluntariosApp.model.dto.user.ContribuicaoDTO;
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
    public void addContribuicao(@RequestBody ContribuicaoDTOs contribuicaoAdd) {
        contribuicaoService.addContribuicao(contribuicaoAdd);
    }

    @GetMapping("/todos")
    public List<ContribuicaoDTO> getContribuicoes() {
        return contribuicaoService.findAllContribuicao();
    }

    @GetMapping("/porOrg")
    public List<ContribuicaoDTO> getContribuicoesOrg(@RequestParam(name = "idOrg") Long idOrg){
        return contribuicaoService.findContribuicoesByOrg(idOrg);
    }

    @GetMapping("/porUser")
    public List<ContribuicaoDTO> getContribuicoesUser(@RequestParam(name = "idUser")Long idUser){
        return contribuicaoService.findContribuicoesByUser(idUser);
    }

    @GetMapping("/porOrg/total")
    public Double getTotalContribuicoesByOrg(@RequestParam(name = "idOrg") Long idOrg){
        return contribuicaoService.getTotalContribuicoesByOrg(idOrg);
    }

    @GetMapping("/porUser/total")
    public Double getTotalContribuicoesByUser(@RequestParam(name = "idUser") Long idUser){
        return contribuicaoService.getTotalContribuicoesByUser(idUser);
    }
}
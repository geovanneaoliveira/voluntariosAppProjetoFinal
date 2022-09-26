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
    public List<ContribuicaoDTO> retornarContribuicoes() {
        return contribuicaoService.buscarContribuicoes();
    }

    @GetMapping("/porOrg")
    public List<ContribuicaoDTO> retornarContribuicoesOrg(@RequestParam(name = "idOrg") Long idOrg) {
        return contribuicaoService.buscarContribuicoesPorOrg(idOrg);
    }

    @GetMapping("/porUser")
    public List<ContribuicaoDTO> retornarContribuicoesUser(@RequestParam(name = "idUser")Long idUser) {
        return contribuicaoService.buscarContribuicoesPorUser(idUser);
    }

    @GetMapping("/porOrg/total")
    public Double retornarTotalContribuicoesPorOrg(@RequestParam(name = "idOrg") Long idOrg) {
        return contribuicaoService.totalContribuicoesPorOrg(idOrg);
    }

    @GetMapping("/porUser/total")
    public Double retornarTotalContribuicoesPorUser(@RequestParam(name = "idUser") Long idUser) {
        return contribuicaoService.totalContribuicoesPorUser(idUser);
    }
}
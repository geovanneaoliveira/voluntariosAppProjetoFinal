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

    /**
     * Adiciona uma nova contribuição ao banco de dados
     * @param contribuicaoAdd
     */
    @PostMapping("/adicionar")
    public void addContribuicao(@RequestBody ContribuicaoDTOs contribuicaoAdd) {
        contribuicaoService.addContribuicao(contribuicaoAdd);
    }

    /**
     * Busca todas as contribuições salvas no banco de dados
     * @return
     */
    @GetMapping("/todos")
    public List<ContribuicaoDTO> retornarContribuicoes() {
        return contribuicaoService.buscarContribuicoes();
    }

    /**
     * Busca todas as contribuições feitas por uma organização
     * @param idOrg
     * @return
     */
    @GetMapping("/porOrg")
    public List<ContribuicaoDTO> retornarContribuicoesOrg(@RequestParam(name = "idOrg") Long idOrg) {
        return contribuicaoService.buscarContribuicoesPorOrg(idOrg);
    }

    /**
     * Busca todas as contribuições feitas por um usuário
     * @param idUser
     * @return
     */
    @GetMapping("/porUser")
    public List<ContribuicaoDTO> retornarContribuicoesUser(@RequestParam(name = "idUser")Long idUser) {
        return contribuicaoService.buscarContribuicoesPorUser(idUser);
    }

    /**
     * Retorna o valor total de todas as contribuições feitas por uma organização
     * @param idOrg
     * @return
     */
    @GetMapping("/porOrg/total")
    public Double retornarTotalContribuicoesPorOrg(@RequestParam(name = "idOrg") Long idOrg) {
        return contribuicaoService.totalContribuicoesPorOrg(idOrg);
    }

    /**
     * Retorna o valor total de todas as contribuições feitas por um usuário
     * @param idUser
     * @return
     */
    @GetMapping("/porUser/total")
    public Double retornarTotalContribuicoesPorUser(@RequestParam(name = "idUser") Long idUser) {
        return contribuicaoService.totalContribuicoesPorUser(idUser);
    }
}
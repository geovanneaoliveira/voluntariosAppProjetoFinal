package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.OrganizacaoBuscaDTO;
import com.entra21.voluntariosApp.model.dto.OrganizacaoDTO;
import com.entra21.voluntariosApp.view.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
public class OrganizacaoRestController {
    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping("/buscar")
    public List<OrganizacaoBuscaDTO> getOrg(@RequestBody String nomeOrg){
        return organizacaoService.getOrgs(nomeOrg);
    }

    @PostMapping("/criar")
    public void criarOrg(@RequestBody OrganizacaoDTO organizacaoDTO){
        organizacaoService.addOrganizacao(organizacaoDTO);
    }
}

package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.OrganizacaoDTO;
import com.entra21.voluntariosApp.view.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/org")
public class OrganizacaoRestController {
    @Autowired
    private OrganizacaoService organizacaoService;

    @PostMapping("/criar")
    public void criarOrg(@RequestBody OrganizacaoDTO organizacaoDTO){
        organizacaoService.addOrganizacao(organizacaoDTO);
    }
}

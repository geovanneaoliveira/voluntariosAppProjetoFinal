package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.OrganizacaoDTOs;
import com.entra21.voluntariosApp.model.dto.user.OrganizacaoDTO;
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
    public List<OrganizacaoDTO> getOrg(@RequestParam(name = "nomeOrg") String nomeOrg){
        return organizacaoService.getOrganizacoes(nomeOrg);
    }

    @PostMapping("/criar")
    public void criarOrg(@RequestBody OrganizacaoDTOs organizacaoDTOs){
        organizacaoService.addOrganizacao(organizacaoDTOs);
    }
    @PutMapping("/atualizar")
    public  void atualizarOrg(@RequestParam(name = "idOrg")Long id, @RequestBody OrganizacaoDTOs dto) {
        organizacaoService.updateOrganizacao(id, dto);
    }
    @PutMapping("/status")
    public void status(@RequestParam(name = "idOrg") Long id) {
        organizacaoService.status(id);
    }

    @GetMapping("/porSupervisor")
    public List<OrganizacaoDTO> buscarOrgPorSurpervisor (@RequestParam(name = "idSupervisor")Long idSupervisor){
       return organizacaoService.buscarOrgsPorSurpervisor(idSupervisor);
    }
}

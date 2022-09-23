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
        return organizacaoService.getOrganizacoes(nomeOrg);
    }

    @PostMapping("/criar")
    public void criarOrg(@RequestBody OrganizacaoDTO organizacaoDTO){
        organizacaoService.addOrganizacao(organizacaoDTO);
    }
    @PutMapping("/atualizar")
    public  void atualizarOrg(@RequestParam(name = "id")Long id, @RequestBody OrganizacaoDTO dto) {
        organizacaoService.updateOrganizacao(id, dto);
    }
    @PutMapping("/status")
    public void status(@RequestParam(name = "id") Long id) {
        organizacaoService.status(id);
    }

    @GetMapping("/porSupervisor")
    public List<OrganizacaoBuscaDTO> buscarOrgPorSurpervisor (@RequestParam(name = "idSupervisor")Long idSupervisor){
       return organizacaoService.buscarOrgsPorSurpervisor(idSupervisor);
    }
}

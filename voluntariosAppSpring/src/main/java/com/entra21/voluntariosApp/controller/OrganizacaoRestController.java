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

    /**
     * Chama um método que busca as organizações pelo nome informado por parâmetro
     *
     * @param nomeOrg
     * @return {code List<OrganizacaoDTO>}
     */
    public List<OrganizacaoDTO> retornarOrg(@RequestBody String nomeOrg) {
        return organizacaoService.retornarOrganizacoes(nomeOrg);
    }

    /**
     * Chama um método que cria uma nova organização no banco de dados
     *
     * @param organizacaoDTOs
     */
    @PostMapping("/criar")
    public void criarOrg(@RequestBody OrganizacaoDTOs organizacaoDTOs) {
        organizacaoService.addOrganizacao(organizacaoDTOs);
    }
   
   /**
     * Chama um método que atualiza os dados de uma organização
     *
     * @param id
     * @param dto
     */
    @PutMapping("/atualizar")
    public  void atualizarOrg(@RequestParam(name = "id")Long idOrg, @RequestBody OrganizacaoDTOs dto) {
        organizacaoService.atualizarOrganizacao(id, dto);
    }

     /**
     * Chama um método que atualiza o status de login da organização
     *
     * @param id
     */
    @PutMapping("/status")
    public void status(@RequestParam(name = "idOrg") Long id) {
        organizacaoService.status(id);
    }

    /**
     * Chama um método que busca as organizações que o supervisor é responsável
     * @param idSupervisor
     * @return {@code List<OrganizacaoDTO>}
     */
    @GetMapping("/porSupervisor")
    public List<OrganizacaoDTO> buscarOrgPorSurpervisor(@RequestParam(name = "idSupervisor") Long idSupervisor) {
       return organizacaoService.buscarOrgsPorSurpervisor(idSupervisor);
    }
}

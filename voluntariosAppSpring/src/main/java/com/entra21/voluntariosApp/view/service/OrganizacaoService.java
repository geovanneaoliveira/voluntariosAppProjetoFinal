package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.OrganizacaoBuscaDTO;
import com.entra21.voluntariosApp.model.dto.OrganizacaoDTO;
import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void addOrganizacao(OrganizacaoDTO organizacaoDTO) {
        pessoaRepository.findById(organizacaoDTO.getIdSupervisor()).ifPresentOrElse(pessoa -> {
            OrganizacaoEntity organizacaoEntity = new OrganizacaoEntity();
            organizacaoEntity.setNome(organizacaoDTO.getNome());
            organizacaoEntity.setDescricao(organizacaoDTO.getDescricao());
            organizacaoEntity.setSupervisor(pessoa);
            organizacaoEntity.setCnpj(organizacaoDTO.getCnpj());
            organizacaoEntity.setAtivo(true);
            organizacaoRepository.save(organizacaoEntity);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");});
    }

    public List<OrganizacaoBuscaDTO> getOrgs(String nomeOrg) {
        List<OrganizacaoEntity> orgs = organizacaoRepository.findAll().stream()
                .filter(org -> org.getNome().toLowerCase().contains(nomeOrg.toLowerCase())).collect(Collectors.toList());
        return orgs.stream().map(orgE -> {
            OrganizacaoBuscaDTO dto = new OrganizacaoBuscaDTO();
            dto.setNomeOrg(orgE.getNome());
            dto.setDescricao(orgE.getDescricao());
            dto.setNomeSupervisor(orgE.getSupervisor().getNome());
            dto.setSobrenomeSupervisor(orgE.getSupervisor().getSobrenome());
            return dto;
        }).collect(Collectors.toList());
    }

    public void atualizarOrganizacao(Long id, OrganizacaoDTO dto) {
        organizacaoRepository.findById(id).ifPresentOrElse(org -> {
            org.setNome(dto.getNome());
            org.setDescricao(dto.getDescricao());
            org.setSupervisor(org.getSupervisor());
            org.setCnpj(dto.getCnpj());
            organizacaoRepository.save(org);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    public void status(Long id){
        organizacaoRepository.findById(id).ifPresentOrElse(org -> {
            org.setAtivo(!org.getAtivo());
            organizacaoRepository.save(org);
        } , () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    public List<OrganizacaoBuscaDTO> buscarOrgPorSurpervisor (Long idSupervisor){
        return organizacaoRepository.findAllBysupervisor_Id(idSupervisor).stream().map(orgs -> {
            OrganizacaoBuscaDTO dto = new OrganizacaoBuscaDTO();
            dto.setNomeOrg(orgs.getNome());
            dto.setDescricao(orgs.getDescricao());
            dto.setNomeSupervisor(orgs.getSupervisor().getNome());
            dto.setSobrenomeSupervisor(orgs.getSupervisor().getSobrenome());
            return dto;
        }).collect(Collectors.toList());

    }
}

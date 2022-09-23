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


    /**
     * Adiciona uma Organização ao repositório.<br>
     * Atributos de OrganizacaoDTO:
     * <li>String nome</li>
     * <li>String descricao</li>
     * <li>Long idSupervisor</li>
     * <li>String cnpj</li>
     * @param organizacaoDTO
     * @throws ResponseStatusException
     */
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

    /**
     * Busca todas as Organizações que possuem nomes similares ao passado por parâmetro.
     * @param nomeOrg
     * @return List {@code <OrganizacaoBuscaDTO>}
     */
    public List<OrganizacaoBuscaDTO> getOrganizacoes(String nomeOrg) {
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

    /**
     * Atualiza a Organização cujo Id for igual ao passado por parâmetro para as informações
     * contidas em um OrganizacaoDTO.
     * <li>String nome</li>
     * <li>String descricao</li>
     * <li>Long idSupervisor</li>
     * <li>String cnpj</li>
     * @param id
     * @param dto
     * @throws ResponseStatusException
     */
    public void updateOrganizacao(Long id, OrganizacaoDTO dto) {
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

    /**
     * Ativa ou desativa uma Organização de acordo com seu Status atual.
     * @param id
     * @throws ResponseStatusException
     * */
    public void status(Long id){
        organizacaoRepository.findById(id).ifPresentOrElse(org -> {
            org.setAtivo(!org.getAtivo());
            organizacaoRepository.save(org);
        } , () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    /**
     * Retorna uma todas as Organizações sob um mesmo supervisor de acordo com o seu Id.
     * @param idSupervisor
     * @return List {@code <OrganizacaoBuscaDTO>}
     */
    public List<OrganizacaoBuscaDTO> buscarOrgsPorSurpervisor (Long idSupervisor){
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

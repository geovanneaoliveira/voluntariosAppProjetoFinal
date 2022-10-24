package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.server.OrganizacaoDTOs;
import com.entra21.voluntariosApp.model.dto.user.OrganizacaoDTO;
import com.entra21.voluntariosApp.model.dto.user.OrganizacaoInfosDTO;
import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ContribuicaoService contribuicaoService;

    /**
     * Adiciona uma Organização ao repositório.<br>
     * Atributos de OrganizacaoDTO:
     * <li>String nome</li>
     * <li>String descricao</li>
     * <li>Long idSupervisor</li>
     * <li>String cnpj</li>
     * <li>String imagePath</li>
     *
     * @param organizacaoDTOs
     * @throws ResponseStatusException
     */
    public void addOrganizacao(OrganizacaoDTOs organizacaoDTOs) {
        pessoaRepository.findById(organizacaoDTOs.getIdSupervisor()).ifPresentOrElse(pessoa -> {
            OrganizacaoEntity organizacaoEntity = new OrganizacaoEntity();
            organizacaoEntity.setNome(organizacaoDTOs.getNome());
            organizacaoEntity.setDescricao(organizacaoDTOs.getDescricao());
            organizacaoEntity.setOrgFoto(organizacaoDTOs.getOrgFoto());
            organizacaoEntity.setSupervisor(pessoa);
            organizacaoEntity.setCnpj(organizacaoDTOs.getCnpj());
            organizacaoEntity.setAtivo(true);
            organizacaoRepository.save(organizacaoEntity);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");
        });
    }

    /**
     * Busca todas as Organizações que possuem nomes similares ao passado por parâmetro.
     *
     * @param nomeOrg
     * @return {@code List<OrganizacaoBuscaDTO>}
     */
    public List<OrganizacaoDTO> retornarOrganizacoes(String nomeOrg) {
        List<OrganizacaoEntity> orgs = organizacaoRepository.findAll().stream().filter(org -> org.getNome().toLowerCase().contains(nomeOrg.toLowerCase())).collect(Collectors.toList());
        return orgs.stream().map(orgE -> {
            OrganizacaoDTO dto = new OrganizacaoDTO();
            dto.setNomeOrg(orgE.getNome());
            dto.setDescricao(orgE.getDescricao());
            dto.setNomeSupervisor(orgE.getSupervisor().getNome());
            dto.setSobrenomeSupervisor(orgE.getSupervisor().getSobrenome());
            dto.setOrgFoto(orgE.getOrgFoto());
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
     * <li>String imagePath</li>
     *
     * @param id
     * @param dto
     * @throws ResponseStatusException
     */
    public void atualizarOrganizacao(Long id, OrganizacaoDTOs dto) {
        organizacaoRepository.findById(id).ifPresentOrElse(org -> {
            org.setNome(dto.getNome());
            org.setDescricao(dto.getDescricao());
            org.setSupervisor(org.getSupervisor());
            org.setCnpj(dto.getCnpj());
            org.setOrgFoto(dto.getOrgFoto());
            organizacaoRepository.save(org);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    /**
     * Ativa ou desativa uma Organização de acordo com seu Status atual.
     *
     * @param id
     * @throws ResponseStatusException
     */
    public void status(Long id) {
        organizacaoRepository.findById(id).ifPresentOrElse(org -> {
            org.setAtivo(!org.getAtivo());
            organizacaoRepository.save(org);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    /**
     * Retorna uma todas as Organizações sob um mesmo supervisor de acordo com o seu Id.
     *
     * @param idSupervisor
     * @return List {@code <OrganizacaoBuscaDTO>}
     */
    public List<OrganizacaoDTO> buscarOrgsPorSurpervisor(Long idSupervisor) {
        return organizacaoRepository.findAllBysupervisor_Id(idSupervisor).stream().map(orgE -> {
            OrganizacaoDTO dto = new OrganizacaoDTO();
            dto.setNomeOrg(orgE.getNome());
            dto.setDescricao(orgE.getDescricao());
            dto.setNomeSupervisor(orgE.getSupervisor().getNome());
            dto.setSobrenomeSupervisor(orgE.getSupervisor().getSobrenome());
            dto.setOrgFoto(orgE.getOrgFoto());
            dto.setId(orgE.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<OrganizacaoInfosDTO> todasOrgs() {
        return organizacaoRepository.findAll().stream().map(orgE -> {
            OrganizacaoInfosDTO dto = new OrganizacaoInfosDTO();
            dto.setNomeOrg(orgE.getNome());
            dto.setDescricao(orgE.getDescricao());
            dto.setNomeSupervisor(orgE.getSupervisor().getNome());
            dto.setSobrenomeSupervisor(orgE.getSupervisor().getSobrenome());
            dto.setCaminhoImagem(orgE.getOrgFoto());
            dto.setId(orgE.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public OrganizacaoDTO buscarOrgsPorId(Long idOrg) {
        OrganizacaoDTO dto = new OrganizacaoDTO();
        organizacaoRepository.findById(idOrg).ifPresentOrElse(oE -> {
            dto.setOrgFoto(oE.getOrgFoto());
            dto.setNomeOrg(oE.getNome());
            dto.setDescricao(oE.getDescricao());
            dto.setNomeSupervisor(oE.getSupervisor().getNome());
            dto.setSobrenomeSupervisor(oE.getSupervisor().getSobrenome());
            dto.setCnpj(oE.getCnpj());
            dto.setId(oE.getId());
            dto.setValorTotal(contribuicaoService.totalContribuicoesPorOrg(oE.getId()));
            dto.setEventos(eventoService.buscarEventoPorIdOrg(idOrg));
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
        return dto;
    }
}

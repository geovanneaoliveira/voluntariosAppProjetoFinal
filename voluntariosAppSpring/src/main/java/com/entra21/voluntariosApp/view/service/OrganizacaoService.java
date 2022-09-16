package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.OrganizacaoDTO;
import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            organizacaoRepository.save(organizacaoEntity);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");});
    }
}

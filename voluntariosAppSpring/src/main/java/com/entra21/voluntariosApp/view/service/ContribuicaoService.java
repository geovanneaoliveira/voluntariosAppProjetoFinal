package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.ContribuicaoDTO;
import com.entra21.voluntariosApp.model.entity.ContribuicaoEntity;
import com.entra21.voluntariosApp.view.repository.ContribuicaoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContribuicaoService {

    @Autowired
    private ContribuicaoRepository contribuicaoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void addContribuicao(ContribuicaoDTO input) {
        ContribuicaoEntity cE = new ContribuicaoEntity();
        cE.setData(input.getData());
        cE.setValor(input.getValor());
        cE.setIdOrganizacao(organizacaoRepository.findById(input.getIdOrganizacao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        cE.setIdPessoa(pessoaRepository.findById(input.getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        contribuicaoRepository.save(cE);
    }

    public List<ContribuicaoDTO> findAllContribuicao(){
        return contribuicaoRepository.findAll().stream().map(FC -> {
            ContribuicaoDTO cdto= new ContribuicaoDTO();
            cdto.setData(FC.getData());
            cdto.setValor(FC.getValor());
            cdto.setIdOrganizacao(FC.getId());
            cdto.setIdPessoa(FC.getId());
            return cdto;
        } ).collect(Collectors.toList());
    }
}
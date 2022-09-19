package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.GetContribuicoesDTO;
import com.entra21.voluntariosApp.model.dto.ContribuicaoDTO;
import com.entra21.voluntariosApp.model.entity.ContribuicaoEntity;
import com.entra21.voluntariosApp.view.repository.ContribuicaoRepository;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContribuicaoService {

    @Autowired
    private ContribuicaoRepository contribuicaoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public void addContribuicao(ContribuicaoDTO input) {
        ContribuicaoEntity cE = new ContribuicaoEntity();
        cE.setData(input.getData());
        cE.setValor(input.getValor());
        cE.setOrganizacao(organizacaoRepository.findById(input.getIdOrganizacao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        cE.setPessoa(pessoaRepository.findById(input.getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        contribuicaoRepository.save(cE);
    }

    public List<GetContribuicoesDTO> findAllContribuicao() {
        return contribuicaoRepository.findAll().stream().map(cE -> {
            GetContribuicoesDTO cdto = new GetContribuicoesDTO();
            cdto.setData(cE.getData());
            cdto.setValor(cE.getValor());
            cdto.setNomeOrg(cE.getOrganizacao().getNome());
            cdto.setNomeUsuario(cE.getPessoa().getNome());
            cdto.setSobrenome(cE.getPessoa().getSobrenome());
            return cdto;
        }).collect(Collectors.toList());
    }

    public List<GetContribuicoesDTO> findContribuicoesByOrg(Long idOrg) {
        return contribuicaoRepository.findAll().stream()
                .filter(cE -> Objects.equals(cE.getOrganizacao().getId(), idOrg)).map(cE -> {
                    GetContribuicoesDTO gcd = new GetContribuicoesDTO();
                    gcd.setData(cE.getData());
                    gcd.setValor(cE.getValor());
                    gcd.setNomeOrg(cE.getOrganizacao().getNome());
                    gcd.setNomeUsuario(cE.getPessoa().getNome());
                    gcd.setSobrenome(cE.getPessoa().getSobrenome());
                    return gcd;
                }).collect(Collectors.toList());
    }
}
package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.PessoaDTO;
import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PessoaService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void cadastrar(PessoaDTO input) {
        PessoaEntity pE = new PessoaEntity();
        pE.setNome(input.getNome());
        pE.setSobrenome(input.getSobrenome());
        pE.setCpf(input.getCpf());
        pE.setTelefone(input.getTelefone());
        pE.setLogin(input.getLogin());
        pE.setSenha(input.getSenha());
        pessoaRepository.save(pE);
    }

    public void atualizar(String antigoLogin, PessoaDTO dto) {
        PessoaEntity pE = pessoaRepository.findByLogin(antigoLogin);
        pE.setNome(dto.getNome());
        pE.setSobrenome(dto.getSobrenome());
        pE.setCpf(dto.getCpf());
        pE.setTelefone(dto.getTelefone());
        pE.setLogin(dto.getLogin());
        pE.setSenha(dto.getSenha());
        pessoaRepository.save(pE);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity pessoa = pessoaRepository.findByLogin(username);
        if (pessoa == null) {
            throw new UsernameNotFoundException(username);
        }
        return pessoa;
    }
}

package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.server.PessoaDTO;
import com.entra21.voluntariosApp.model.dto.user.LoginDTO;
import com.entra21.voluntariosApp.model.dto.user.LoginSemIdDTO;
import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class PessoaService implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Cria um novo usuário de acordo com as informações passadas por um PessoaDTO<br>
     * Atributos de PessoaDTO:
     * <li>String nome</li>
     * <li>String sobrenome</li>
     * <li>String cpf</li>
     * <li>String telefone</li>
     * <li>String login</li>
     * <li>String senha</li>
     *
     * @param pessoaDTO
     */
    public void cadastrar(PessoaDTO pessoaDTO) throws IOException {
        PessoaEntity pE = new PessoaEntity();
        pE.setNome(pessoaDTO.getNome());
        pE.setSobrenome(pessoaDTO.getSobrenome());
        pE.setCpf(pessoaDTO.getCpf());
        pE.setTelefone(pessoaDTO.getTelefone());
        pE.setLogin(pessoaDTO.getLogin());
        pE.setSenha(pessoaDTO.getSenha());
        pE.setAtivo(true);
        pE.setFotoPerfil(pessoaDTO.getFotoPerfil());
        pessoaRepository.save(pE);
    }

    /**
     * Atualiza o Usuário cujo login for igual ao passado por parâmetro com as
     * informações recebidas por um PessoaDTO:
     * <li>String nome</li>
     * <li>String sobrenome</li>
     * <li>String cpf</li>
     * <li>String telefone</li>
     * <li>String login</li>
     * <li>String senha</li>
     *
     * @param antigoLogin
     * @param dto
     */
    public void atualizar(String antigoLogin, PessoaDTO dto) throws IOException {
        PessoaEntity pE = pessoaRepository.findByLogin(antigoLogin);
        pE.setNome(dto.getNome());
        pE.setSobrenome(dto.getSobrenome());
        pE.setCpf(dto.getCpf());
        pE.setTelefone(dto.getTelefone());
        pE.setFotoPerfil(dto.getFotoPerfil());
        pE.setLogin(dto.getLogin());
        pE.setSenha(dto.getSenha());
        pessoaRepository.save(pE);
    }

    /**
     * Ativa ou desativa um usuário de acordo com seu Status atual.
     *
     * @param login
     */
    public void status(String login) {
        PessoaEntity pE = pessoaRepository.findByLogin(login);
        pE.setAtivo(!pE.getAtivo());
        pessoaRepository.save(pE);
    }

    /**
     * Retorna os detalhes de um usuário de acordo com o login dele
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity pessoa = pessoaRepository.findByLogin(username);
        if (pessoa == null) {
            throw new UsernameNotFoundException(username);
        }
        return pessoa;
    }

    public LoginDTO login(LoginSemIdDTO loginSemIdDTO) {
        PessoaEntity pE = pessoaRepository.findByLogin(loginSemIdDTO.getUsername());
        if(pE.getPassword().equals(loginSemIdDTO.getPassword())){
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setLogin(pE.getUsername());
            loginDTO.setSenha(pE.getPassword());
            loginDTO.setId(pE.getId());
            return loginDTO;
        }
        return null;
    }
}

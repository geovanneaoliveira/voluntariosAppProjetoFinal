package com.entra21.voluntariosApp.model.entity;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "pessoa")
public class PessoaEntity implements UserDetails {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "caminho_imagem")
    private String caminhoImagem;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @ManyToMany
    @JoinTable(name = "tag_interesse_pessoa", joinColumns = @JoinColumn(name = "id_pessoa", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_tag", referencedColumnName = "id"))
    private List<TagsEntity> tags;

    @OneToMany
    @JoinTable(name = "contribuicao", joinColumns = @JoinColumn(name = "id_organizacao", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_pessoa", referencedColumnName = "id"))
    private List<ContribuicaoEntity> contribuicoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

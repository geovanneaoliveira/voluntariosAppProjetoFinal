package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
    PessoaEntity findByLogin(String login);
}

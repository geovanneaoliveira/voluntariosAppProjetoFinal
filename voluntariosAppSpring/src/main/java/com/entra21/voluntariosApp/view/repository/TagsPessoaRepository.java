package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.TagsInteressePessoaEntity;
import com.entra21.voluntariosApp.model.entity.TagsPessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsPessoaRepository extends JpaRepository<TagsPessoaEntity, Long> {
    List<TagsPessoaEntity> findAllBypessoa_Id(Long idPessoa);
}

package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrocinadorRepository extends JpaRepository<PatrocinadorEntity, Long> {
}

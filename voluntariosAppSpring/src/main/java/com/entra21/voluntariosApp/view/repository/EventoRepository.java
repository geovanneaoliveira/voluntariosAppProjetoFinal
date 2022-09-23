package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
    List<EventoEntity> findAllBytags_Id(Long idTag);
}

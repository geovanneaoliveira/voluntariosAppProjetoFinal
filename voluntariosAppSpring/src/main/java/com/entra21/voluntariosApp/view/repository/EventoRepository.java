package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
<<<<<<< Updated upstream
=======
    List<EventoEntity> findAllBytags_Id(Long idTag);

<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}

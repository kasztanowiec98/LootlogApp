package org.example.lootlogkopalniany.Entities.Repositories;

import org.example.lootlogkopalniany.Entities.EqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EqEntityRepository extends JpaRepository<EqEntity, Long> {
    List<EqEntity> findAllByOrderByInsertDateDesc(Pageable pageable);
}
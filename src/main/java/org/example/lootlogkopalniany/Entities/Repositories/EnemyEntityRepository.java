package org.example.lootlogkopalniany.Entities.Repositories;

import org.example.lootlogkopalniany.Entities.EnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemyEntityRepository extends JpaRepository<EnemyEntity, Long> {
    boolean existsByEnemyid(String enemyid);
}

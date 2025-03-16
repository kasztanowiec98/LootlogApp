package org.example.lootlogkopalniany.Entities.newRepositories;

import org.example.lootlogkopalniany.Entities.newEntities.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Integer> {
}

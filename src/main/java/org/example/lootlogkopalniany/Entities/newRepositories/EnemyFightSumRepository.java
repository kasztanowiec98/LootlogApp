package org.example.lootlogkopalniany.Entities.newRepositories;

import org.example.lootlogkopalniany.Entities.newEntities.EnemyFightSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyFightSumRepository extends JpaRepository<EnemyFightSum, Integer> {
}

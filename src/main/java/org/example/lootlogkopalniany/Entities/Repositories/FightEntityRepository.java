package org.example.lootlogkopalniany.Entities.Repositories;

import org.example.lootlogkopalniany.Entities.FightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightEntityRepository extends JpaRepository<FightEntity, Long> {
}

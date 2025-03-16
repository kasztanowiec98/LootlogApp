package org.example.lootlogkopalniany.Entities.Repositories;

import org.example.lootlogkopalniany.Entities.FightPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightPlayerEntityRepository extends JpaRepository<FightPlayerEntity, Long> {
}

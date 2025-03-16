package org.example.lootlogkopalniany.Entities.newRepositories;

import org.example.lootlogkopalniany.Entities.newEntities.PlayerFight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerFightRepository extends JpaRepository<PlayerFight, Integer> {
}

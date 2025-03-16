package org.example.lootlogkopalniany.Entities.newRepositories;

import org.example.lootlogkopalniany.Entities.newEntities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}

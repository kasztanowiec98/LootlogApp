package org.example.lootlogkopalniany.Entities.newRepositories;

import org.example.lootlogkopalniany.Entities.newEntities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {
}

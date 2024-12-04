package org.example.lootlogkopalniany.Entities.PageUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PageUserRepository extends JpaRepository<PageUser, Long> {
    Optional<PageUser> findByUsername(String username); // Correct return type
}
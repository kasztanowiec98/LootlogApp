package org.example.lootlogkopalniany.Entities.Repositories;

import org.example.lootlogkopalniany.Entities.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapperRepository extends JpaRepository<UserMapper, Long> {
    UserMapper findByUserid(String userid);
}

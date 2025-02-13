package org.example.lootlogkopalniany.Services;

import org.example.lootlogkopalniany.Entities.Repositories.EnemyEntityRepository;
import org.example.lootlogkopalniany.Entities.Repositories.UserMapperRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    private final UserMapperRepository userMapperRepository;
    private final EnemyEntityRepository enemyEntityRepository;

    public ValidatorService(UserMapperRepository userMapperRepository, EnemyEntityRepository enemyEntityRepository) {
        this.userMapperRepository = userMapperRepository;
        this.enemyEntityRepository = enemyEntityRepository;
    }

    public boolean isUserValid(String userId) {
        return userMapperRepository.findUsernameByUserid(userId) != null;
    }

    public boolean isEnemyValid(String enemyId) {
        return enemyEntityRepository.existsByEnemyid(enemyId);
    }
}

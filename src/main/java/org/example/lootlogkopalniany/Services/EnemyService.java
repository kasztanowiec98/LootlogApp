package org.example.lootlogkopalniany.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.lootlogkopalniany.Entities.EnemyEntity;
import org.example.lootlogkopalniany.Entities.Repositories.EnemyEntityRepository;
import org.example.lootlogkopalniany.RequestsClasses.EnemyRequest;
import org.example.lootlogkopalniany.RequestsClasses.SaveLootRequest;
import org.springframework.stereotype.Service;

@Service
public class EnemyService {

    private final EnemyEntityRepository enemyEntityRepository;
    private final ValidatorService validatorService;


    public EnemyService(EnemyEntityRepository enemyEntityRepository, ValidatorService validatorService) {
        this.enemyEntityRepository = enemyEntityRepository;
        this.validatorService = validatorService;
    }

    public boolean addEnemy(SaveLootRequest request) {
        try {
            if (request.getEnemies() == null || request.getEnemies().isEmpty()) {
                System.out.println("Brak przeciwników do dodania.");
                return true;
            }

            for (EnemyRequest enemy : request.getEnemies()) {
                if (validatorService.isEnemyValid(enemy.getEnemyid())) {
                    System.out.println("Przeciwnik już istnieje: " + enemy.getEnemyid());
                    continue; // Pomijamy istniejącego przeciwnika
                }

                EnemyEntity enemyEntity = new EnemyEntity();
                enemyEntity.setEnemyname(enemy.getEnemyname());
                enemyEntity.setEnemyid(enemy.getEnemyid());
                enemyEntityRepository.save(enemyEntity);
            }

            return true;
        } catch (Exception e) {
            System.err.println("Błąd zapisu przeciwnika: " + e.getMessage());
            return false;
        }
    }

}

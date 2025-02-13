package org.example.lootlogkopalniany.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.lootlogkopalniany.Entities.EnemyEntity;
import org.example.lootlogkopalniany.Entities.Repositories.EnemyEntityRepository;
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

    public boolean addEnemy(SaveLootRequest request){
        try {
            // Walidacja przeciwnika
            if (validatorService.isEnemyValid(request.getEnemyid())) {
                return true; // Przeciwnik już istnieje, nie dodajemy duplikatu
            }

            EnemyEntity enemyEntity = new EnemyEntity();
            enemyEntity.setEnemyname(request.getEnemyname());
            enemyEntity.setEnemyid(request.getEnemyid());

            enemyEntityRepository.save(enemyEntity);
            return true;
        } catch (Exception e) {
            System.err.println("Błąd zapisu przeciwnika: " + e.getMessage());
            return false;
        }
    }
}

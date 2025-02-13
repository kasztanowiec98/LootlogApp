package org.example.lootlogkopalniany.Services;

import org.example.lootlogkopalniany.RequestsClasses.SaveLootRequest;
import org.springframework.stereotype.Service;

@Service
public class LootService {
    private final EqService eqService;
    private final EnemyService enemyService;
    private final ValidatorService validatorService;

    public LootService(EqService eqService, EnemyService enemyService, ValidatorService validatorService) {
        this.eqService = eqService;
        this.enemyService = enemyService;
        this.validatorService = validatorService;
    }

    public boolean processLoot(SaveLootRequest request) {
        if (!validatorService.isUserValid(request.getUser())) {
            System.out.println("Błąd: użytkownik nie istnieje w bazie -> " + request.getUser());
            return false;
        }

        // Jeśli użytkownik istnieje, przetwarzamy ekwipunek i przeciwnika
        boolean eqSaved = eqService.processAndSaveEquipment(request);
        boolean enemySaved = enemyService.addEnemy(request);

        return eqSaved && enemySaved;
    }
}

package org.example.lootlogkopalniany.Services;

import org.example.lootlogkopalniany.Entities.EqEntity;
import org.example.lootlogkopalniany.Entities.FightEntity;
import org.example.lootlogkopalniany.Entities.FightPlayerEntity;
import org.example.lootlogkopalniany.Entities.Repositories.EqEntityRepository;
import org.example.lootlogkopalniany.Entities.Repositories.FightEntityRepository;
import org.example.lootlogkopalniany.Entities.Repositories.FightPlayerEntityRepository;
import org.example.lootlogkopalniany.LootMapper;
import org.example.lootlogkopalniany.RequestsClasses.SaveLootRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LootService {
    private final FightEntityRepository fightRepository;
    private final EqEntityRepository eqEntityRepository;
    private final FightPlayerEntityRepository fightPlayerRepository;
    private final LootMapper lootMapper;

    public LootService(FightEntityRepository fightRepository, EqEntityRepository eqEntityRepository,
                       FightPlayerEntityRepository fightPlayerRepository, LootMapper lootMapper) {
        this.fightRepository = fightRepository;
        this.eqEntityRepository = eqEntityRepository;
        this.fightPlayerRepository = fightPlayerRepository;
        this.lootMapper = lootMapper;
    }

    public boolean processAndSaveEquipment(SaveLootRequest request) {
        try {
            FightEntity fightEntity = new FightEntity();
            fightEntity.setUserId(request.getUser());

            List<EqEntity> loot = lootMapper.toEqEntities(request.getItems());
            loot.forEach(eq -> eq.setFight(fightEntity));
            fightEntity.setLoot(loot);

            List<FightPlayerEntity> players = lootMapper.toFightPlayerEntities(request.getPlayers());
            players.forEach(player -> player.setFight(fightEntity));
            fightEntity.setPlayers(players);

            fightRepository.save(fightEntity);

            return true;
        } catch (Exception e) {
            System.err.println("Błąd zapisu ekwipunku: " + e.getMessage());
            return false;
        }
    }

    public List<FightEntity> getFightHistory() {
        return fightRepository.findAll();
    }
}


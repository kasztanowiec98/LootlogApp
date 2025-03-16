package org.example.lootlogkopalniany.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.lootlogkopalniany.Entities.*;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Entities.Repositories.*;
import org.example.lootlogkopalniany.RequestsClasses.ItemRequest;
import org.example.lootlogkopalniany.RequestsClasses.SaveLootRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.lootlogkopalniany.RequestsClasses.EnemyRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EqService {

    private final EqEntityRepository eqEntityRepository;
    private final UserMapperRepository userMapperRepository;
    private final ValidatorService validatorService;

    private final FightEntityRepository fightRepository;
    private final PlayerEntityRepository playerRepository;
    private final FightPlayerEntityRepository fightPlayerRepository;

    public EqService(EqEntityRepository eqEntityRepository, UserMapperRepository userMapperRepository, ValidatorService validatorService, FightEntityRepository fightRepository, PlayerEntityRepository playerRepository, FightPlayerEntityRepository fightPlayerRepository) {
        this.eqEntityRepository = eqEntityRepository;
        this.userMapperRepository = userMapperRepository;
        this.validatorService = validatorService;
        this.fightRepository = fightRepository;
        this.playerRepository = playerRepository;
        this.fightPlayerRepository = fightPlayerRepository;
    }

    public boolean processAndSaveEquipment(SaveLootRequest request) {
        try {
            if (!validatorService.isUserValid(request.getUser())) {
                System.out.println("Użytkownik nie istnieje: " + request.getUser());
                return false;
            }

            // Tworzymy nową walkę
            FightEntity fightEntity = new FightEntity();
            fightEntity.setUserId(request.getUser());

            List<EqEntity> loot = request.getItems().stream()
                    .map(item -> {
                        EqEntity eqEntity = new EqEntity();
                        eqEntity.setItemname(item.getItemname());
                        eqEntity.setItemnumber(item.getItemnumber());
                        eqEntity.setRarity(item.getRarity());
                        eqEntity.setIkona(item.getIcon());
                        eqEntity.setFight(fightEntity);
                        return eqEntity;
                    }).collect(Collectors.toList());

            fightEntity.setLoot(loot);

            List<FightPlayerEntity> players = request.getPlayers().stream()
                    .map(player -> {
                        PlayerEntity playerEntity = playerRepository.findByName(player.getName())
                                .orElseGet(() -> {
                                    PlayerEntity newPlayer = new PlayerEntity();
                                    newPlayer.setName(player.getName());
                                    return newPlayer;
                                });

                        FightPlayerEntity fightPlayer = new FightPlayerEntity();
                        fightPlayer.setProf(player.getProf());
                        fightPlayer.setLvl(player.getLvl());
                        fightPlayer.setIcon(player.getIcon());
                        fightPlayer.setFight(fightEntity);
                        fightPlayer.setPlayer(playerEntity);

                        return fightPlayer;
                    }).collect(Collectors.toList());

            fightEntity.setPlayers(players);

            fightRepository.save(fightEntity);
            return true;
        } catch (Exception e) {
            System.err.println("Błąd zapisu ekwipunku: " + e.getMessage());
            return false;
        }
    }

    public List<EqEntityDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EqEntity> entities = eqEntityRepository.findAllByOrderByInsertDateDesc(pageable);

        // Mapaowanie EqEntity na EqEntityDTO
        return entities.stream()
                .map(entity -> new EqEntityDTO(
                        entity.getItemname(),
                        entity.getRarity(),
                        entity.getItemnumber(),
                        entity.getUsername() != null ? entity.getUsername(): "Brak użytkownika",
                        entity.getIkona(),  // Dodanie daty do DTO
                        entity.getInsertDate()
                ))
                .collect(Collectors.toList());
    }
    public long getUserCount() {
        return eqEntityRepository.count();
    }
}


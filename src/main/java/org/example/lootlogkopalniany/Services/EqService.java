package org.example.lootlogkopalniany.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Entities.EqEntity;
import org.example.lootlogkopalniany.Entities.Repositories.EqEntityRepository;
import org.example.lootlogkopalniany.Entities.Repositories.UserMapperRepository;
import org.example.lootlogkopalniany.Entities.UserMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EqService {

    private final EqEntityRepository eqEntityRepository;
    private final UserMapperRepository userMapperRepository;
    private final ObjectMapper objectMapper;

    public EqService(EqEntityRepository eqEntityRepository, UserMapperRepository userMapperRepository) {
        this.eqEntityRepository = eqEntityRepository;
        this.userMapperRepository = userMapperRepository;
        this.objectMapper = new ObjectMapper();
    }

    public boolean processAndSaveEquipment(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String userId = jsonNode.path("user").asText();
            JsonNode stateJsonNode = jsonNode.path("loot").path("states");
            String stateNumber = stateJsonNode.fieldNames().next();

            String itemName = jsonNode.path("item").path(stateNumber).path("name").asText();
            String itemNumber = jsonNode.path("item").path(stateNumber).path("hid").asText();
            String rarity = HelperService.extractRarity(jsonNode.path("item").path(stateNumber).path("stat").asText());

            UserMapper userMapper = userMapperRepository.findByUserid(userId);
            if (userMapper == null) return false;

            EqEntity eqEntity = new EqEntity();
            eqEntity.setItemname(itemName);
            eqEntity.setItemnumber(itemNumber);
            eqEntity.setRarity(rarity);
            eqEntity.setUsermapper(userMapper);
            eqEntityRepository.save(eqEntity);
            return true;
        } catch (Exception e) {
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
                        entity.getUsermapper() != null ? entity.getUsermapper().getUsername() : "Brak użytkownika",
                        entity.getInsertDate()  // Dodanie daty do DTO
                ))
                .collect(Collectors.toList());
    }
    public long getUserCount() {
        return eqEntityRepository.count();
    }
}


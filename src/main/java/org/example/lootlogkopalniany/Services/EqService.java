package org.example.lootlogkopalniany.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Entities.EqEntity;
import org.example.lootlogkopalniany.Entities.Repositories.EqEntityRepository;
import org.example.lootlogkopalniany.Entities.Repositories.UserMapperRepository;
import org.example.lootlogkopalniany.Entities.UserMapper;
import org.example.lootlogkopalniany.RequestsClasses.SaveLootRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EqService {

    private final EqEntityRepository eqEntityRepository;
    private final UserMapperRepository userMapperRepository;
    private final ValidatorService validatorService;

    public EqService(EqEntityRepository eqEntityRepository, UserMapperRepository userMapperRepository, ValidatorService validatorService) {
        this.eqEntityRepository = eqEntityRepository;
        this.userMapperRepository = userMapperRepository;
        this.validatorService = validatorService;
    }

    public boolean processAndSaveEquipment(SaveLootRequest request) {
        try {
            // Walidacja użytkownika
            if (!validatorService.isUserValid(request.getUser())) {
                System.out.println("Użytkownik nie istnieje: " + request.getUser());
                return false;
            }

            // Pobranie nazwy użytkownika
            UserMapper userMapper = userMapperRepository.findUsernameByUserid(request.getUser());
            if (userMapper == null) return false;

            EqEntity eqEntity = new EqEntity();
            eqEntity.setIkona(request.getIkona());
            eqEntity.setItemname(request.getItem());
            eqEntity.setItemnumber(request.getItemnumber());
            eqEntity.setRarity(request.getRarity());
            eqEntity.setUsername(userMapper.getUsername());

            eqEntityRepository.save(eqEntity);
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


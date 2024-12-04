package org.example.lootlogkopalniany.Entities;

import jakarta.servlet.http.HttpServletRequest;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Entities.Repositories.EqEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
public class EqService {

    @Autowired
    private EqEntityRepository eqEntityRepository;

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


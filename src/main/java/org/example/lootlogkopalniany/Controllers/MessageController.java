package org.example.lootlogkopalniany.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lootlogkopalniany.Entities.ActivationUserRequest;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Entities.FightEntity;
import org.example.lootlogkopalniany.Services.EqService;
import org.example.lootlogkopalniany.RequestsClasses.*;
import org.example.lootlogkopalniany.Services.LootService;
import org.example.lootlogkopalniany.Services.UserMapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API", description = "API for user management")
@RestController
@RequestMapping("/api")
public class MessageController {

    private final EqService eqService;
    private final LootService lootService;
    private final UserMapperService userMapperService;

    public MessageController(EqService eqService, LootService lootService, UserMapperService userMapperService) {
        this.eqService = eqService;
        this.lootService = lootService;
        this.userMapperService = userMapperService;
    }

    @Tag(name = "save loot", description = "loot save from game")
    @PostMapping("/save")
    public ResponseEntity<String> saveLoot(@RequestBody SaveLootRequest request) {
        boolean saved = lootService.processAndSaveEquipment(request);
        if (saved) {
            return ResponseEntity.ok("Loot zapisany.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd zapisu.");
    }

    @GetMapping("/history")
    public ResponseEntity<List<FightEntity>> getFightHistory() {
        List<FightEntity> history = lootService.getFightHistory();
        return ResponseEntity.ok(history);
    }

    @GetMapping("/users")
    public List<EqEntityDTO> getUsers(@RequestParam int page) {
        return eqService.getUsers(page, 10);
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(@RequestBody DeleteRequest request) {
        return userMapperService.deleteUser(request.getUserid());
    }

    @PutMapping("/updateuser")
    public ResponseEntity<String> updateUser(@RequestBody UpdateRequest request) {
        return userMapperService.updateUser(request);
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest request) {
        return userMapperService.addUser(request);
    }

    @PostMapping("/active")
    public ResponseEntity<String> isActiveUser(@RequestBody ActivationUserRequest message) {
        return userMapperService.setActiveUser(message.getUserid());
    }
}

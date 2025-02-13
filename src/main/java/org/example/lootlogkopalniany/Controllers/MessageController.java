package org.example.lootlogkopalniany.Controllers;

import org.example.lootlogkopalniany.Entities.ActivationUserRequest;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Services.EqService;
import org.example.lootlogkopalniany.RequestsClasses.*;
import org.example.lootlogkopalniany.Services.LootService;
import org.example.lootlogkopalniany.Services.UserMapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/save")
    public ResponseEntity<String> receiveMessage(@RequestBody SaveLootRequest request) {
        boolean saved = lootService.processLoot(request);
        if (saved) {
            return ResponseEntity.ok("Zapisano do bazy danych (ekwipunek + przeciwnik).");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Błąd: użytkownik nie istnieje w bazie.");
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

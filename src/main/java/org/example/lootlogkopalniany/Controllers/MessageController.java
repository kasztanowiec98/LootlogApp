package org.example.lootlogkopalniany.Controllers;

import org.example.lootlogkopalniany.Entities.ActivationUserRequest;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Services.EqService;
import org.example.lootlogkopalniany.RequestsClasses.*;
import org.example.lootlogkopalniany.Services.UserMapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final EqService eqService;
    private final UserMapperService userMapperService;

    public MessageController(EqService eqService, UserMapperService userMapperService) {
        this.eqService = eqService;
        this.userMapperService = userMapperService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> receiveMessage(@RequestBody String message) {
        boolean saved = eqService.processAndSaveEquipment(message);
        if (saved) {
            return ResponseEntity.ok("Zapisano do bazy danych.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd przetwarzania wiadomości.");
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

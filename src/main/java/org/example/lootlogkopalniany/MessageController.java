package org.example.lootlogkopalniany;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.lootlogkopalniany.Entities.ActivationUserRequest;
import org.example.lootlogkopalniany.Entities.DTO.EqEntityDTO;
import org.example.lootlogkopalniany.Entities.EqEntity;
import org.example.lootlogkopalniany.Entities.Repositories.EqEntityRepository;
import org.example.lootlogkopalniany.Entities.EqService;
import org.example.lootlogkopalniany.Entities.Repositories.UserMapperRepository;
import org.example.lootlogkopalniany.Entities.UserMapper;
import org.example.lootlogkopalniany.RequestsClasses.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EqEntityRepository eqEntityRepository; // Wstrzykujemy repozytorium
    @Autowired
    private EqService eqService;
    @Autowired
    private UserMapperService userMapperService;

    @Autowired
    private UserMapperRepository userMapperRepository;

    @PostMapping("/save")
    public void receiveMessage(@RequestBody String message, HttpServletRequest request) {
        try {
            // Parsowanie JSON-a
            JsonNode jsonNode = objectMapper.readTree(message);

            // Wydobycie userId
            String userId = jsonNode.path("user").asText();
            JsonNode stateJsonNode = jsonNode.path("loot").path("states");
            String stateNumber = stateJsonNode.fieldNames().next();
            System.out.println(stateNumber);

            // Wydobycie itemName, itemNumber i rarity
            String itemName = jsonNode.path("item").path(stateNumber).path("name").asText();
            String itemNumber = jsonNode.path("item").path(stateNumber).path("hid").asText();
            String rarity = extractRarity(jsonNode.path("item").path(stateNumber).path("stat").asText());

            // Załaduj UserMapper z bazy danych na podstawie userId
            UserMapper userMapper = userMapperRepository.findByUserid(userId);

            if (userMapper == null) {
                System.err.println("Nie znaleziono użytkownika o id: " + userId);
                return; // Zakończ metodę, jeśli użytkownik nie istnieje
            }

            // Tworzenie obiektu EqEntity
            EqEntity eqEntity = new EqEntity();
            eqEntity.setItemname(itemName);
            eqEntity.setItemnumber(itemNumber);
            eqEntity.setRarity(rarity);
            eqEntity.setUsermapper(userMapper);

            // Zapisywanie obiektu EqEntity do bazy danych
            eqEntityRepository.save(eqEntity);

            // Wyświetlenie danych w konsoli
            System.out.println("Zapisano do bazy danych: " + eqEntity);
            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            System.err.println("Błąd podczas przetwarzania wiadomości: " + e.getMessage());
        }
    }
    @GetMapping("/users")
    public List<EqEntityDTO> getUsers(@RequestParam int page) {
        try {
            // Pobieramy listę EqEntityDTO z serwisu
            List<EqEntityDTO> eqEntities = eqService.getUsers(page, 10);
            // Zwracamy tę listę jako odpowiedź
            return eqEntities;
        } catch (Exception e) {
            System.err.println("Błąd podczas pobierania użytkowników: " + e.getMessage());
            return Collections.emptyList();  // Jeśli coś pójdzie nie tak, zwracamy pustą listę
        }
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody AddUserRequest request) {

        UserMapper userMapper = new UserMapper();
        userMapper.setIsActive("true");
        userMapper.setUsername(request.getName());
        userMapper.setUserid(userMapperService.extractPlayerId(request.getUrl()));

        userMapperRepository.save(userMapper);

        return "Saved";

    }


    @PostMapping ("/active")
    public void isActiveUser(@RequestBody ActivationUserRequest message){
        UserMapper userMapper = userMapperRepository.findByUserid(message.getUserid());
        userMapper.setIsActive("true");
        userMapperRepository.save(userMapper);
    }

    private String extractRarity(String stat) {
        // Szukamy wzoru rarity=wartość w stringu stat
        Pattern pattern = Pattern.compile("rarity=([^;]+)");
        Matcher matcher = pattern.matcher(stat);
        return matcher.find() ? matcher.group(1) : "brak";
    }
}

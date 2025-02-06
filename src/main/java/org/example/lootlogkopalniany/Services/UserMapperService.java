package org.example.lootlogkopalniany.Services;

import org.example.lootlogkopalniany.Entities.Repositories.UserMapperRepository;
import org.example.lootlogkopalniany.Entities.UserMapper;
import org.example.lootlogkopalniany.RequestsClasses.AddUserRequest;
import org.example.lootlogkopalniany.RequestsClasses.UpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserMapperService {

    private final UserMapperRepository userMapperRepository;

    public UserMapperService(UserMapperRepository userMapperRepository) {
        this.userMapperRepository = userMapperRepository;
    }

    public ResponseEntity<String> deleteUser(String userId) {
        UserMapper user = userMapperRepository.findByUserid(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userMapperRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully.");
    }

    public ResponseEntity<String> updateUser(UpdateRequest request) {
        UserMapper user = userMapperRepository.findByUserid(request.getUserid());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        if (request.getNewuserid() != null) {
            user.setUserid(request.getNewuserid());
        }
        userMapperRepository.save(user);
        return ResponseEntity.ok("User updated successfully.");
    }

    public ResponseEntity<String> addUser(AddUserRequest request) {
        UserMapper userMapper = new UserMapper();
        userMapper.setIsActive("true");
        userMapper.setUsername(request.getName());
        userMapper.setUserid(extractPlayerId(request.getUrl()));

        userMapperRepository.save(userMapper);
        return ResponseEntity.ok("User saved.");
    }

    public ResponseEntity<String> setActiveUser(String userId) {
        UserMapper userMapper = userMapperRepository.findByUserid(userId);
        if (userMapper == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userMapper.setIsActive("true");
        userMapperRepository.save(userMapper);
        return ResponseEntity.ok("User activated.");
    }

    public String extractPlayerId(String url) {
        try {
            Pattern pattern = Pattern.compile("view,(\\d+)");
            Matcher matcher = pattern.matcher(url);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new IllegalArgumentException("URL nie zawiera poprawnego formatu ID gracza.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas przetwarzania URL: " + e.getMessage());
        }
    }
}

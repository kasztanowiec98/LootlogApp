package org.example.lootlogkopalniany;

import org.example.lootlogkopalniany.Entities.Repositories.UserMapperRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserMapperService {

    private final UserMapperRepository userMapperRepository;

    public UserMapperService(UserMapperRepository userMapperRepository) {
        this.userMapperRepository = userMapperRepository;
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

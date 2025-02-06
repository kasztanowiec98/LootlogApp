package org.example.lootlogkopalniany.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lootlogkopalniany.RequestsClasses.LoginRequest;
import org.example.lootlogkopalniany.RequestsClasses.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("message", "User registered successfully");
        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String token = authService.authenticate(loginRequest);

        // Tworzenie ciasteczka
        Cookie jwtCookie = new Cookie("jwt_token", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setDomain("cypis-ll.pl");
        jwtCookie.setMaxAge(60 * 60 * 24);

        // Dodanie ciasteczka do odpowiedzi
        response.addCookie(jwtCookie);

        // Zwrot odpowiedzi JSON
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/t")
    public String getToken(@RequestBody String string){
        return "asda";
    }
}


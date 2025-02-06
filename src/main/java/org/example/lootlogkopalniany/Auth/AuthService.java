package org.example.lootlogkopalniany.Auth;

import org.example.lootlogkopalniany.Entities.PageUsers.PageUserRepository;
import org.example.lootlogkopalniany.Entities.PageUsers.PageUser;
import org.example.lootlogkopalniany.RequestsClasses.LoginRequest;
import org.example.lootlogkopalniany.RequestsClasses.RegisterRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final PageUserRepository pageUserRepository;

    public AuthService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, PageUserRepository pageUserRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.pageUserRepository = pageUserRepository;
    }

    public void register(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirmation())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        PageUser user = new PageUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Set.of("USER"));
        pageUserRepository.save(user);
    }

    public String authenticate(LoginRequest loginRequest) {
        PageUser user = pageUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        throw new BadCredentialsException("Invalid credentials");
    }
}

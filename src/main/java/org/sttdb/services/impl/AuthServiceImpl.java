package org.sttdb.services.impl;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.sttdb.configurations.PasswordUtils;
import org.sttdb.dto.auth.LoginRequestDto;
import org.sttdb.dto.auth.LoginResponseDto;
import org.sttdb.entities.User;
import org.sttdb.repositories.UserRepository;
import org.sttdb.services.AuthService;

import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    @Inject
    UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findOptionalByUsername(dto.username()).orElseThrow(
                () -> new EntityNotFoundException("User " + dto.username() + "not found"));

        if (!PasswordUtils.verifyPassword(dto.password(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }



        String jwt = generateJWT(
                user.getUsername(),
                user.getRole()
        );
        return LoginResponseDto.builder()
                .jwt(jwt)
                .build();
    }

    public String generateJWT(String username, String role) {
        return Jwt.issuer("https://example.com/issuer")
                .upn("rullyadmiral@gmail.com")
                .groups(new HashSet<>(Collections.singletonList(role)))
                .claim("username", username)
                .claim("role", role)
                .sign();
    }
}

package org.sttdb.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sttdb.dto.user.UserChangePasswordRequestDto;
import org.sttdb.dto.user.UserRegistrationRequestDto;
import org.sttdb.dto.user.UserRegistrationResponseDto;
import org.sttdb.entities.User;
import org.sttdb.repositories.UserRepository;
import org.sttdb.services.UserService;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserRegistrationResponseDto> getUserByRole(String role) {
        return userRepository.findByRole(role).stream().map(
                user -> UserRegistrationResponseDto.builder()
                        .username(user.getUsername())
                        .role(user.getRole())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public void addUser(UserRegistrationRequestDto dto) {
        User user = userRepository.findByUsername(dto.username());
        if (user != null && userRepository.isPersistent(user)){
            throw new EntityExistsException("User " + dto.username() + " already exists");
        }
        if (!dto.password().equals(dto.confirmPassword())){
            throw new IllegalArgumentException("Confirm password does not match");
        }
        var newUser = User.builder()
                .username(dto.username())
                .password(dto.password())
                .role(dto.role())
                .build();

        userRepository.persist(newUser);
    }

    @Override
    @Transactional
    public void changePassword(UserChangePasswordRequestDto dto) {
        User user = userRepository.findByUsername(dto.username());
        if (user == null) {
            throw new EntityNotFoundException("User " + dto.username() + " does not exist");
        }
        if (!user.getPassword().equals(dto.oldPassword())){
            throw new IllegalArgumentException("Old password does not match");
        }
        if (!dto.newPassword().equals(dto.confirmPassword())){
            throw new IllegalArgumentException("Confirm password does not match");
        }
        user.setPassword(dto.confirmPassword());
        userRepository.persist(user);
    }
}

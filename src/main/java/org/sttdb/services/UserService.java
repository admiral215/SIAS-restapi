package org.sttdb.services;

import org.sttdb.dto.user.UserChangePasswordRequestDto;
import org.sttdb.dto.user.UserRegistrationRequestDto;
import org.sttdb.dto.user.UserRegistrationResponseDto;

import java.util.List;

public interface UserService {
    List<UserRegistrationResponseDto> getUserByRole(String role);

    void addUser(UserRegistrationRequestDto dto);

    void changePassword(UserChangePasswordRequestDto dto);

    boolean isUsernameExist(String username);
}

package org.sttdb.dto.user;

public record UserChangePasswordRequestDto(
        String username,
        String oldPassword,
        String newPassword,
        String confirmPassword
) {
}

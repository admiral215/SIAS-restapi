package org.sttdb.dto.user;

import lombok.Builder;

@Builder
public record UserRegistrationRequestDto(
        String username,
        String password,
        String confirmPassword,
        String role
) {
}

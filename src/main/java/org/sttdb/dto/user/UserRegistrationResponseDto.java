package org.sttdb.dto.user;

import lombok.Builder;

@Builder
public record UserRegistrationResponseDto(
        String username,
        String role
) {
}

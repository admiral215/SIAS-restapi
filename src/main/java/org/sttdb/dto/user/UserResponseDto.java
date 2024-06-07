package org.sttdb.dto.user;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String username,
        String message
) {
}

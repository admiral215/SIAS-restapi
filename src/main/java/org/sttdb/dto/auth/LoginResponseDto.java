package org.sttdb.dto.auth;

import lombok.Builder;

@Builder
public record LoginResponseDto(
        String jwt
) {
}

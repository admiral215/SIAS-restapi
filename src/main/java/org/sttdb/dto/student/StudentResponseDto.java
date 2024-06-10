package org.sttdb.dto.student;

import lombok.Builder;

@Builder
public record StudentResponseDto(
        String username,
        String name,
        String message
) {
}

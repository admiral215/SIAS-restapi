package org.sttdb.dto.major;

import lombok.Builder;

@Builder
public record MajorResponseDto(
        Long id,
        String message
) {
}

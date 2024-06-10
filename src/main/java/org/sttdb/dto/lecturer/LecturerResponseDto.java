package org.sttdb.dto.lecturer;

import lombok.Builder;

@Builder
public record LecturerResponseDto(
        String username,
        String message
) {
}

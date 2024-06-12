package org.sttdb.dto.major;

import lombok.Builder;

@Builder
public record MajorItemResponseDto(
        Long id,
        String name,
        String department,
        String faculty,
        String type,
        String level,
        String description,
        Integer totalCredits
) {
}

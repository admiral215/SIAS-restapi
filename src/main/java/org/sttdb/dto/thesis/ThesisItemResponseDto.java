package org.sttdb.dto.thesis;

import lombok.Builder;

@Builder
public record ThesisItemResponseDto(
        Long id,
        String studentName,
        String status,
        String title,
        String abstractField,
        String supervisorName1,
        String supervisorName2
) {
}

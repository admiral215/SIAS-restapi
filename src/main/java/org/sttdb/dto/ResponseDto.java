package org.sttdb.dto;

import lombok.Builder;

@Builder
public record ResponseDto(
        String message
) {
}

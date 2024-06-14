package org.sttdb.dto.thesis;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ThesisStatusRequestDto(
        @NotNull
        Long thesisId,

        @NotNull
        @NotBlank
        String status,

        String description
) {
}

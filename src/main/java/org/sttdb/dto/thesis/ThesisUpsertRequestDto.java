package org.sttdb.dto.thesis;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record ThesisUpsertRequestDto(
        Long id,

        @NotNull
        @NotBlank
        String title,

        @NotNull
        @NotBlank
        String abstractField,

        @NotNull
        @NotBlank
        String studentId
) {
}

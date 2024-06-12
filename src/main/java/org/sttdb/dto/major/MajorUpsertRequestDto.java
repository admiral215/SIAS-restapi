package org.sttdb.dto.major;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record MajorUpsertRequestDto(
        Long id,

        @NotBlank
        @NotNull
        String name,

        @NotBlank
        @NotNull
        String department,

        @NotBlank
        @NotNull
        String faculty,

        @NotBlank
        @NotNull
        String type,

        @NotBlank
        @NotNull
        String level,

        String description,

        @NotNull
        Integer totalCredits
) {

}

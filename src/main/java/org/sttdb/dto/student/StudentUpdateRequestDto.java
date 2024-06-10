package org.sttdb.dto.student;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.sttdb.validator.Username;

import java.time.LocalDate;

@Builder
public record StudentUpdateRequestDto(

        @NotBlank
        @NotNull
        String firstName,

        String lastName,

        @NotBlank
        @NotNull
        @Size(min = 8, max = 13)
        String phoneNumber,

        String email,

        @NotNull
        LocalDate birthDate,

        @NotBlank
        @NotNull
        String gender,

        @NotBlank
        @NotNull
        String birthCity,

        @NotBlank
        @NotNull
        String address,

        @NotNull
        Integer totalCredits
) {
        public String getFullName(){
                return firstName + " " + lastName;
        }
}

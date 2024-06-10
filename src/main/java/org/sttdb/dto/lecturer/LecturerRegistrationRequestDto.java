package org.sttdb.dto.lecturer;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.sttdb.validator.Username;

import java.time.LocalDate;

public record LecturerRegistrationRequestDto(
        @NotNull
        @NotBlank
        @Username
        String userId,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String confirmPassword,

        @NotNull
        @NotBlank
        String firstName,

        String lastName,

        @NotNull
        @NotBlank
        String phoneNumber,

        String email,


        LocalDate birthDate,

        @NotNull
        @NotBlank
        String gender,

        @NotNull
        @NotBlank
        String birthCity,

        @NotNull
        @NotBlank
        String address
) {
}

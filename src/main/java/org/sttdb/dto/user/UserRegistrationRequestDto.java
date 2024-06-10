package org.sttdb.dto.user;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.sttdb.validator.Username;

@Builder
public record UserRegistrationRequestDto(
        @NotNull
        @NotBlank
        @Username(message = "username sudah dipakai")
        String username,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String confirmPassword,

        @NotNull
        @NotBlank
        String role
) {
}

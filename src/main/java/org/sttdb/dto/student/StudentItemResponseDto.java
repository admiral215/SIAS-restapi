package org.sttdb.dto.student;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentItemResponseDto(
        String userId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        LocalDate birthDate,
        Long age,
        String gender,
        String birthCity,
        String address,
        Integer totalCredits
) {
    public String getFullName(){
        return firstName + " " + lastName;
    }
}

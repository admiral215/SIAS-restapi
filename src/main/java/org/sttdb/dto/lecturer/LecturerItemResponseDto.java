package org.sttdb.dto.lecturer;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LecturerItemResponseDto(
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
        LocalDate registerDate
) {

}

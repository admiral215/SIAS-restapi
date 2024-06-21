package org.sttdb.dto.auth;
import lombok.Builder;

@Builder
public record LoginRequestDto(
        String username,
        String password
) {

}

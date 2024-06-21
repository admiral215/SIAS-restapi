package org.sttdb.services;

import org.sttdb.dto.auth.LoginRequestDto;
import org.sttdb.dto.auth.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}

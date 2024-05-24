package org.web.mywebsite.services.interfaces;

import jakarta.mail.MessagingException;
import org.web.mywebsite.dtos.auth.AuthResponseDto;
import org.web.mywebsite.dtos.auth.LoginDto;
import org.web.mywebsite.dtos.auth.SignUpDto;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.user.UserDto;

public interface AuthService {
    CommonResponseDto<AuthResponseDto> login(LoginDto loginDto);

    CommonResponseDto<UserDto> register(SignUpDto signUpDto) throws MessagingException;

    CommonResponseDto<String> logout();
}

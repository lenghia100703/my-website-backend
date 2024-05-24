package org.web.mywebsite.controllers.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.web.mywebsite.controllers.interfaces.AuthController;
import org.web.mywebsite.dtos.auth.AuthResponseDto;
import org.web.mywebsite.dtos.auth.LoginDto;
import org.web.mywebsite.dtos.auth.SignUpDto;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.user.UserDto;
import org.web.mywebsite.services.interfaces.AuthService;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    AuthService authService;

    @Override
    public CommonResponseDto<UserDto> register(SignUpDto signUpDto) throws MessagingException {
        return authService.register(signUpDto);
    }

    @Override
    public CommonResponseDto<AuthResponseDto> login(LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @Override
    public CommonResponseDto<String> logout() {
        return authService.logout();
    }
}

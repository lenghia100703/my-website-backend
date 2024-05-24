package org.web.mywebsite.controllers.interfaces;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.mywebsite.dtos.auth.AuthResponseDto;
import org.web.mywebsite.dtos.auth.LoginDto;
import org.web.mywebsite.dtos.auth.SignUpDto;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.user.UserDto;

@RequestMapping("/api/auth")
public interface AuthController {
    @PostMapping("/register")
    CommonResponseDto<UserDto> register(@RequestBody SignUpDto signUpDto) throws MessagingException;

    @PostMapping("/login")
    CommonResponseDto<AuthResponseDto> login(@RequestBody LoginDto loginDto);

    @PostMapping("/logout")
    CommonResponseDto<String> logout();
}

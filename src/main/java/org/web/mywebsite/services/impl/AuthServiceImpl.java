package org.web.mywebsite.services.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.web.mywebsite.dtos.auth.AuthResponseDto;
import org.web.mywebsite.dtos.auth.LoginDto;
import org.web.mywebsite.dtos.auth.SignUpDto;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.mail.MailConfirmDto;
import org.web.mywebsite.dtos.user.UserDto;
import org.web.mywebsite.dtos.user.UserInfoInToken;
import org.web.mywebsite.entities.UserEntity;
import org.web.mywebsite.entities.VerificationTokenEntity;
import org.web.mywebsite.enums.ResponseCode;
import org.web.mywebsite.enums.Role;
import org.web.mywebsite.enums.TypeToken;
import org.web.mywebsite.exceptions.CommonException;
import org.web.mywebsite.repositories.UserRepository;
import org.web.mywebsite.repositories.VerificationTokenRepository;
import org.web.mywebsite.securities.JWTProvider;
import org.web.mywebsite.services.interfaces.AuthService;
import org.web.mywebsite.services.interfaces.MailService;
import org.web.mywebsite.utils.SecurityContextUtil;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTProvider jwtProvider;

    @Value("${default.avatar}")
    String defaultAvatar;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    MailService mailService;

    private final HttpServletResponse response;

    public AuthServiceImpl(AuthenticationManager authenticationManager, HttpServletResponse response) {
        this.authenticationManager = authenticationManager;
        this.response = response;
    }

    @Override
    public CommonResponseDto<AuthResponseDto> login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        Optional<UserEntity> optionalUser = userRepository.findUserByEmail(loginDto.getEmail());

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            String accessToken = jwtProvider.generateAccessToken(response, new UserInfoInToken(user.getId()), String.valueOf(user.getRole()));
            String refreshToken = jwtProvider.generateRefreshToken(response, new UserInfoInToken(user.getId()), String.valueOf(user.getRole()));
            user.setAccessToken(accessToken);
            user.setRefreshToken(refreshToken);
            AuthResponseDto authResponse = new AuthResponseDto(user.getId(), accessToken, refreshToken);
            userRepository.save(user);
            return new CommonResponseDto<>(authResponse);
        } else {
            return new CommonResponseDto<>(ResponseCode.ERROR);
        }
    }

    @Override
    public CommonResponseDto<UserDto> register(SignUpDto signUpDto) throws MessagingException {
        if (userRepository.findUserByEmail(signUpDto.getEmail()).isPresent()) {
            throw new CommonException(ResponseCode.ERROR, "Email đã tồn tại");
        }

        UserEntity user = new UserEntity();
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setRole(Role.valueOf(signUpDto.getRole()));
        user.setAvatar(defaultAvatar);
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setCreatedBy(signUpDto.getEmail());
        VerificationTokenEntity verificationToken = createVerificationToken(user, TypeToken.CONFIRM);
        mailService.sendConfirmationEmail(new MailConfirmDto(verificationToken));

        return new CommonResponseDto<>(new UserDto(userRepository.save(user)));
    }

    @Override
    public CommonResponseDto<String> logout() {
        Long id = SecurityContextUtil.getCurrentUserId();
        UserEntity currentUser = userRepository.findById(id).get();
        currentUser.setAccessToken(null);
        currentUser.setRefreshToken(null);
        userRepository.save(currentUser);
        SecurityContextHolder.clearContext();

        ResponseCookie jwtCookie = ResponseCookie.from("jwt", null)
                .maxAge(1000)
                .httpOnly(true).path("/").secure(true).sameSite("None").build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        ResponseCookie jwtRefreshCookie = ResponseCookie.from("jwt-refresh", null)
                .maxAge(1000)
                .httpOnly(true).path("/").secure(true).sameSite("None").build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

        return new CommonResponseDto<>("Logged out successfully");
    }

    private VerificationTokenEntity createVerificationToken(UserEntity user, TypeToken type) {
        String token = UUID.randomUUID().toString().replace("-", "");
        VerificationTokenEntity myToken = new VerificationTokenEntity(token, user, type);
        return verificationTokenRepository.save(myToken);
    }
}

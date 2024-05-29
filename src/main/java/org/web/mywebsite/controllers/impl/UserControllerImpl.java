package org.web.mywebsite.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.web.mywebsite.controllers.interfaces.UserController;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.user.AddUserDto;
import org.web.mywebsite.dtos.user.ChangePasswordDto;
import org.web.mywebsite.dtos.user.UserDto;
import org.web.mywebsite.services.interfaces.UserService;

import java.io.IOException;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;

    @Override
    public CommonResponseDto<UserDto> getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public CommonResponseDto<UserDto> getCurrentUser() {
        return new CommonResponseDto<>(new UserDto(userService.getCurrentUser()));
    }

    @Override
    public PaginatedDataDto<UserDto> getUserByPage(int page) {
        return userService.getUserByPage(page);
    }

    @Override
    public CommonResponseDto<UserDto> createUser(AddUserDto addUserDto) {
        return userService.createUser(addUserDto);
    }

    @Override
    public CommonResponseDto<String> editUser(Long id, MultipartFile file, String username, String email, String age, String gradle, String school, String avatarUrl, String phone) throws IOException {
        return userService.editUser(id, email, username, phone, age, gradle, school, avatarUrl, file);
    }

    @Override
    public CommonResponseDto<String> deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    @Override
    public CommonResponseDto<String> changePassword(Long id, ChangePasswordDto changePasswordDto) {
        return userService.changePassword(id, changePasswordDto);
    }
}

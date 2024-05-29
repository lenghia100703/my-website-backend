package org.web.mywebsite.controllers.interfaces;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.user.AddUserDto;
import org.web.mywebsite.dtos.user.ChangePasswordDto;
import org.web.mywebsite.dtos.user.UserDto;

import java.io.IOException;

@RequestMapping("/api/user")
public interface UserController {
    @GetMapping("/{id}")
    CommonResponseDto<UserDto> getUserById(@PathVariable("id") Long id);

    @GetMapping("/me")
    CommonResponseDto<UserDto> getCurrentUser();

    @GetMapping("")
    PaginatedDataDto<UserDto> getUserByPage(@RequestParam(name = "page") int page);

    @PostMapping("")
    CommonResponseDto<UserDto> createUser(@RequestBody AddUserDto addUserDto);

    @PutMapping("/{id}")
    CommonResponseDto<String> editUser(@PathVariable("id") Long id,
                                       @RequestParam(value = "avatar", required = false) MultipartFile file,
                                       @RequestParam("username") String username,
                                       @RequestParam("email") String email,
                                       @RequestParam("age") String age,
                                       @RequestParam("gradle") String gradle,
                                       @RequestParam("school") String school,
                                       @RequestParam("avatarUrl") String avatarUrl,
                                       @RequestParam("phone") String phone) throws IOException;


    @DeleteMapping("/{id}")
    CommonResponseDto<String> deleteUser(@PathVariable("id") Long id);

    @PutMapping("/change-password/{id}")
    CommonResponseDto<String> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDto changePasswordDto);

}

package org.web.mywebsite.services.interfaces;


import org.springframework.web.multipart.MultipartFile;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.user.AddUserDto;
import org.web.mywebsite.dtos.user.ChangePasswordDto;
import org.web.mywebsite.dtos.user.UserDto;
import org.web.mywebsite.entities.UserEntity;

import java.io.IOException;

public interface UserService {
    public CommonResponseDto<UserDto> getUserById(Long id);

    public UserEntity getCurrentUser();

    public PaginatedDataDto<UserDto> getUserByPage(int page);

    public CommonResponseDto<UserDto> createUser(AddUserDto addUserDto);

    public CommonResponseDto<String> editUser(Long id, String email, String username, String phone,
                                              String age, String gradle, String school, String avatarUrl, MultipartFile file) throws IOException;

    public CommonResponseDto<String> deleteUser(Long id);

    public CommonResponseDto<String> changePassword(Long id, ChangePasswordDto changePasswordDto);

    public UserEntity findByEmail(String email);

    public Long getCurrentUserId();
}

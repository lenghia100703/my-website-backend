package org.web.mywebsite.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.web.mywebsite.constants.PageableConstants;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.user.AddUserDto;
import org.web.mywebsite.dtos.user.ChangePasswordDto;
import org.web.mywebsite.dtos.user.UserDto;
import org.web.mywebsite.entities.UserEntity;
import org.web.mywebsite.enums.ResponseCode;
import org.web.mywebsite.enums.Role;
import org.web.mywebsite.exceptions.CommonException;
import org.web.mywebsite.repositories.UserRepository;
import org.web.mywebsite.services.interfaces.UserService;
import org.web.mywebsite.utils.GithubUtil;
import org.web.mywebsite.utils.SecurityContextUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.web.mywebsite.enums.ResponseCode.ERROR;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GithubUtil githubUtil;


    @Value("${default.avatar}")
    String defaultAvatar;

    @Override
    public CommonResponseDto<UserDto> getUserById(Long id) {
        return new CommonResponseDto<>(new UserDto(Objects.requireNonNull(userRepository.findById(id).orElse(null))));
    }

    @Override
    public UserEntity getCurrentUser() {
        Long id = getCurrentUserId();
        return userRepository.getById(id);
    }

    @Override
    public PaginatedDataDto<UserDto> getUserByPage(int page) {
        List<UserEntity> allUsers = userRepository.findAllUserByRole();
        if (page >= 1) {
            Pageable pageable = PageRequest.of(page - 1, PageableConstants.LIMIT);
            Page<UserEntity> userPage = userRepository.findAll(pageable);

            List<UserDto> userDtos = userPage.getContent().stream()
                    .filter(user -> !user.getRole().equals(Role.ADMIN))
                    .map(UserDto::new)
                    .collect(Collectors.toList());

            return new PaginatedDataDto<>(userDtos, page, allUsers.toArray().length);
        } else {
            List<UserDto> userDtos = allUsers.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
            return new PaginatedDataDto<>(userDtos, 1, allUsers.toArray().length);
        }

    }

    @Override
    public CommonResponseDto<UserDto> createUser(AddUserDto addUserDto) {
        if (findByEmail(addUserDto.getEmail()) != null) {
            throw new CommonException(ERROR, "Email đã tồn tại");
        }

        UserEntity user = new UserEntity();
        user.setEmail(addUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(addUserDto.getPassword()));
        user.setUsername(addUserDto.getUsername());
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setCreatedBy(getCurrentUser().getEmail());
        user.setAvatar(defaultAvatar);
        user.setRole(Role.valueOf(addUserDto.getRole()));

        return new CommonResponseDto<>(new UserDto(userRepository.save(user)));
    }

    @Override
    public CommonResponseDto<String> editUser(Long id, String email, String username, String phone,
                                              String age, String gradle, String school, String avatarUrl, MultipartFile file) throws IOException {
        UserEntity user = userRepository.getById(id);
        if (user == null) {
            throw new CommonException(ResponseCode.NOT_FOUND, "Không tìm thấy người dùng!");
        }

        user.setEmail(email);
        user.setUsername(username);
        user.setUpdatedBy(user.getEmail());
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        user.setAge(age);
        user.setGradle(gradle);
        user.setSchool(school);
        user.setPhone(phone);

        if (file != null) {
            user.setAvatar(githubUtil.uploadImage(file, "avatar"));
        }

        if (!Objects.equals(avatarUrl, "")) {
            user.setAvatar(avatarUrl);
        } else if (Objects.equals(avatarUrl, "")) {
            user.setAvatar(user.getAvatar());
        }

        userRepository.save(user);
        return new CommonResponseDto<>("Edited successfully");
    }

    @Override
    public CommonResponseDto<String> deleteUser(Long id) {
        UserEntity user = userRepository.getById(id);

        if (user == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }

        userRepository.delete(user);
        return new CommonResponseDto<>("Deleted successfully");
    }

    @Override
    public CommonResponseDto<String> changePassword(Long id, ChangePasswordDto changePasswordDto) {
        UserEntity userEntity = userRepository.getById(id);
        if (userEntity == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }

        if (!changePasswordDto.getPassword().equals("") && passwordEncoder.matches(changePasswordDto.getCurrentPassword(), userEntity.getPassword())) {
            if (changePasswordDto.getPassword().length() < 8) {
                throw new CommonException(ERROR, "Mật khẩu phải có ít nhất 8 ký tự");
            }
            userEntity.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        }
        userRepository.save(userEntity);

        return new CommonResponseDto<>("Change password successfully");
    }

    @Override
    public UserEntity findByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findUserByEmail(email);
        return userOptional.orElse(null);
    }

    @Override
    public Long getCurrentUserId() {
        return SecurityContextUtil.getCurrentUserId();
    }
}

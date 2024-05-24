package org.web.mywebsite.dtos.user;

import jakarta.persistence.Column;
import lombok.Data;
import org.web.mywebsite.dtos.course.CourseDto;
import org.web.mywebsite.entities.CourseEntity;
import org.web.mywebsite.entities.UserEntity;
import org.web.mywebsite.enums.AuthProvider;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String role;
    private String avatar;
    private String age;
    private String gradle;
    private String provider;
    private String providerId;
    private Boolean emailConfirmed;
    private Boolean active;
    private String school;
    private List<CourseDto> courses;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public UserDto(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole().toString();
        this.avatar = user.getAvatar();
        this.age = user.getAge();
        this.gradle = user.getGradle();
        this.provider = user.getProvider().toString();
        this.providerId = user.getProviderId();
        this.emailConfirmed = user.getEmailConfirmed();
        this.active = user.getActive();
        this.school = user.getSchool();
        this.courses = user.getCourses().stream().map(CourseDto::new).toList();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.createdBy = user.getCreatedBy();
        this.updatedBy = user.getUpdatedBy();
    }
}

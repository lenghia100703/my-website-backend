package org.web.mywebsite.dtos.course;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.web.mywebsite.dtos.document.DocumentDto;
import org.web.mywebsite.dtos.user.UserDto;
import org.web.mywebsite.entities.CourseEntity;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
public class CourseDto {
    private Long id;
    private String name;
    private String code;
    private List<DocumentDto> documents;
    private UserDto user;
    private Boolean isPublic;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public CourseDto(CourseEntity course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.isPublic = course.getIsPublic();
        this.user = new UserDto(course.getUser());
        this.image = course.getImage();
        this.documents = course.getDocuments().stream().map(DocumentDto::new).toList();
        this.createdAt = course.getCreatedAt();
        this.updatedAt = course.getUpdatedAt();
        this.createdBy = course.getCreatedBy();
        this.updatedBy = course.getUpdatedBy();
    }
}

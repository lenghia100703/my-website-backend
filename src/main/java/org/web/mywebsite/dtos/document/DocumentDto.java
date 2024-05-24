package org.web.mywebsite.dtos.document;

import lombok.Data;
import org.web.mywebsite.dtos.course.CourseDto;
import org.web.mywebsite.entities.DocumentEntity;

import java.util.Date;

@Data
public class DocumentDto {
    private Long id;
    private String name;
    private CourseDto course;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public DocumentDto(DocumentEntity document) {
        this.id = document.getId();
        this.name = document.getName();
        this.course = new CourseDto(document.getCourse());
        this.createdAt = document.getCreatedAt();
        this.updatedAt = document.getUpdatedAt();
        this.createdBy = document.getCreatedBy();
        this.updatedBy = document.getUpdatedBy();
    }
}

package org.web.mywebsite.dtos.course;

import lombok.Data;

@Data
public class EditCourseDto {
    private String name;
    private String code;
    private String image;
    private Boolean isPublic;
}

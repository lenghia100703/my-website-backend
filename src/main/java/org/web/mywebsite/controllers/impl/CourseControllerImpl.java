package org.web.mywebsite.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.web.mywebsite.controllers.interfaces.CourseController;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.course.AddCourseDto;
import org.web.mywebsite.dtos.course.CourseDto;
import org.web.mywebsite.dtos.course.EditCourseDto;
import org.web.mywebsite.services.interfaces.CourseService;

@RestController
public class CourseControllerImpl implements CourseController {
    @Autowired
    CourseService courseService;

    @Override
    public CommonResponseDto<CourseDto> createCourse(AddCourseDto addCourseDto) {
        return courseService.createCourse(addCourseDto);
    }

    @Override
    public PaginatedDataDto<CourseDto> getCourseByPage(int page) {
        return courseService.getCourseByPage(page);
    }

    @Override
    public CommonResponseDto<CourseDto> getCourseById(Long id) {
        return courseService.getCourseById(id);
    }

    @Override
    public CommonResponseDto<String> editCourse(Long id, EditCourseDto editCourseDto) {
        return courseService.editCourse(id, editCourseDto);
    }

    @Override
    public CommonResponseDto<String> deleteCourse(Long id) {
        return courseService.deleteCourse(id);
    }
}

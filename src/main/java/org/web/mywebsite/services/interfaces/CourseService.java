package org.web.mywebsite.services.interfaces;

import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.course.AddCourseDto;
import org.web.mywebsite.dtos.course.CourseDto;
import org.web.mywebsite.dtos.course.EditCourseDto;

public interface CourseService {
    CommonResponseDto<CourseDto> createCourse(AddCourseDto addCourseDto);

    PaginatedDataDto<CourseDto> getCourseByPage(int page);

    CommonResponseDto<CourseDto> getCourseById(Long id);

    CommonResponseDto<String> editCourse(Long id, EditCourseDto editCourseDto);

    CommonResponseDto<String> deleteCourse(Long id);
}

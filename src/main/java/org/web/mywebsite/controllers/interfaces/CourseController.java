package org.web.mywebsite.controllers.interfaces;

import org.springframework.web.bind.annotation.*;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.course.AddCourseDto;
import org.web.mywebsite.dtos.course.CourseDto;
import org.web.mywebsite.dtos.course.EditCourseDto;

@RequestMapping("/api/course")
public interface CourseController {
    @PostMapping("")
    CommonResponseDto<CourseDto> createCourse(@RequestBody AddCourseDto addCourseDto);

    @GetMapping("")
    PaginatedDataDto<CourseDto> getCourseByPage(@RequestParam("page") int page);

    @GetMapping("/{id}")
    CommonResponseDto<CourseDto> getCourseById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    CommonResponseDto<String> editCourse(@PathVariable("id") Long id, @RequestBody EditCourseDto editCourseDto);

    @DeleteMapping("/{id}")
    CommonResponseDto<String> deleteCourse(@PathVariable("id") Long id);
}

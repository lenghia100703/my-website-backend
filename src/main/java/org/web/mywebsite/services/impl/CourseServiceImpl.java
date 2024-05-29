package org.web.mywebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.web.mywebsite.constants.PageableConstants;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.course.AddCourseDto;
import org.web.mywebsite.dtos.course.CourseDto;
import org.web.mywebsite.dtos.course.EditCourseDto;
import org.web.mywebsite.entities.CourseEntity;
import org.web.mywebsite.enums.ResponseCode;
import org.web.mywebsite.exceptions.CommonException;
import org.web.mywebsite.repositories.CourseRepository;
import org.web.mywebsite.services.interfaces.CourseService;
import org.web.mywebsite.services.interfaces.UserService;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserService userService;

    @Override
    public CommonResponseDto<CourseDto> createCourse(AddCourseDto addCourseDto) {
        CourseEntity course = new CourseEntity();
        course.setName(addCourseDto.getName());
        course.setCode(addCourseDto.getCode());
        course.setImage(addCourseDto.getImage());
        course.setCreatedAt(new Date(System.currentTimeMillis()));
        course.setCreatedBy(userService.getCurrentUser().getEmail());
        course.setIsPublic(addCourseDto.getIsPublic());

        return new CommonResponseDto<>(new CourseDto(courseRepository.save(course)));
    }

    @Override
    public PaginatedDataDto<CourseDto> getCourseByPage(int page) {
        List<CourseEntity> courses = courseRepository.findAll();
        if (page >= 1) {
            Pageable pageable = PageRequest.of(page - 1, PageableConstants.LIMIT);
            Page<CourseEntity> courseByPage = courseRepository.findAll(pageable);

            List<CourseEntity> news = courseByPage.getContent();
            return new PaginatedDataDto<>(news.stream().map(CourseDto::new).toList(), page, courses.toArray().length);

        } else {
            return new PaginatedDataDto<>(courses.stream().map(CourseDto::new).toList(), 1, courses.toArray().length);
        }
    }

    @Override
    public CommonResponseDto<CourseDto> getCourseById(Long id) {
        return new CommonResponseDto<>(new CourseDto(courseRepository.getById(id)));
    }

    @Override
    public CommonResponseDto<String> editCourse(Long id, EditCourseDto editCourseDto) {
        CourseEntity course = courseRepository.getById(id);

        if (course == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }

        course.setName(editCourseDto.getName());
        course.setCode(editCourseDto.getCode());
        course.setImage(editCourseDto.getImage());
        course.setIsPublic(editCourseDto.getIsPublic());
        course.setUpdatedBy(userService.getCurrentUser().getEmail());
        course.setUpdatedAt(new Date(System.currentTimeMillis()));

        courseRepository.save(course);

        return new CommonResponseDto<>("Updated successfully");
    }

    @Override
    public CommonResponseDto<String> deleteCourse(Long id) {
        CourseEntity course = courseRepository.getById(id);

        if (course == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }

        courseRepository.delete(course);
        return new CommonResponseDto<>("Deleted successfully");
    }
}

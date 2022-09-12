package com.ead.course.controllers;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.specifications.SpecificationTemplate;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public interface CourseApi {

    ResponseEntity<Object> saveCourse(CourseDto courseDto, Errors errors);

    ResponseEntity<Object> deleteCourse(UUID courseId);

    ResponseEntity<Object> updateCourse(UUID courseId, CourseDto courseDto);

    ResponseEntity<Page<CourseModel>> getAllCourses(SpecificationTemplate.CourseSpec spec,
                                                    Pageable pageable,
                                                    UUID userId);

    ResponseEntity<Object> getOneCourse(UUID courseId);
}

package com.ead.course.controllers;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface CourseApi {

    ResponseEntity<Object> saveCourse(CourseDto courseDto);

    ResponseEntity<Object> deleteCourse(UUID courseId);

    ResponseEntity<Object> updateCourse(UUID courseId, CourseDto courseDto);

    ResponseEntity<List<CourseModel>> getAllCourses();

    ResponseEntity<Object> getOneCourse(UUID courseId);
}

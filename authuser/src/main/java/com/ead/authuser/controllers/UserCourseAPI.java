package com.ead.authuser.controllers;

import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.UserCourseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserCourseAPI {

    ResponseEntity<Page<CourseDto>> getAllCoursesByUser(Pageable pageable, UUID userId);

    ResponseEntity<Object> saveSubscriptionUserInCourse(UUID userId, UserCourseDto userCourseDto);
}

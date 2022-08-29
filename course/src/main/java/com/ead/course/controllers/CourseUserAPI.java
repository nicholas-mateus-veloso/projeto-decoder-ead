package com.ead.course.controllers;

import com.ead.course.dtos.UserDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseUserAPI {

    ResponseEntity<Page<UserDto>> getAllUsersByCourse(Pageable pageable, UUID courseId);
}

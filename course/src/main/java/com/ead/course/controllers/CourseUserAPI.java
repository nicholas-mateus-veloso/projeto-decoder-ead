package com.ead.course.controllers;

import com.ead.course.dtos.SubscriptionDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseUserAPI {

    ResponseEntity<Object> getAllUsersByCourse(Pageable pageable, UUID courseId);

    ResponseEntity<Object> saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto);
}

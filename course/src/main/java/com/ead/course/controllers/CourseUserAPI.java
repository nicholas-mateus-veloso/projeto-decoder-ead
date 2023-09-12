package com.ead.course.controllers;

import com.ead.course.dtos.SubscriptionDto;
import com.ead.course.specifications.SpecificationTemplate;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseUserAPI {

    ResponseEntity<Object> getAllUsersByCourse(SpecificationTemplate.UserSpec spec, Pageable pageable, UUID courseId);

    ResponseEntity<Object> saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto);
}

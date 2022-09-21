package com.ead.course.controllers;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.dtos.SubscriptionDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.enums.UserStatus;
import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.CourseUserService;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUserController implements CourseUserAPI {

    public static final String COURSE_NOT_FOUND = "Course Not Found.";
    public static final String ERROR_SUBSCRIPTION_ALREADY_EXISTS = "Error: subscription already exists!";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_IS_BLOCKED = "User is blocked.";
    public static final String COURSE_USER_NOT_FOUND = "CourseUser not found.";
    public static final String COURSE_USER_DELETED_SUCCESSFULLY = "CourseUser deleted successfully.";
    private final AuthUserClient authUserClient;

    private final CourseService courseService;

    private final CourseUserService courseUserService;

    public CourseUserController(AuthUserClient authUserClient,
                                CourseService courseService,
                                CourseUserService courseUserService) {
        this.authUserClient = authUserClient;
        this.courseService = courseService;
        this.courseUserService = courseUserService;
    }

    @GetMapping("/courses/{courseId}/users")
    public ResponseEntity<Object> getAllUsersByCourse(@PageableDefault(
            sort = "userId",
            direction = Sort.Direction.ASC) Pageable pageable,
                                                      @PathVariable(value = "courseId") UUID courseId) {
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (courseModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(COURSE_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(authUserClient.getAllUsersByCourse(courseId, pageable));
    }

    @PostMapping("/courses/{courseId}/users/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "courseId") UUID courseId,
                                                               @RequestBody @Valid SubscriptionDto subscriptionDto) {
        ResponseEntity<UserDto> responseUser;
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (courseModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(COURSE_NOT_FOUND);
        }
        if (courseUserService.existsByCourseAndUserId(courseModelOptional.get(), subscriptionDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ERROR_SUBSCRIPTION_ALREADY_EXISTS);
        }

        try {
            responseUser = authUserClient.getOneUserById(subscriptionDto.getUserId());
            if (responseUser.getBody().getUserStatus().equals(UserStatus.BLOCKED)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(USER_IS_BLOCKED);
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
            }
        }

        CourseUserModel courseUserModel = courseUserService.saveAndSendSubscriptionUserInCourse(courseModelOptional
                .get()
                .convertToCourseUserModel(subscriptionDto.getUserId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(courseUserModel);
    }

    @DeleteMapping("/courses/users/{userId}")
    public ResponseEntity<Object> deleteCourseUserByUser(@PathVariable(value = "userId") UUID userId) {
        if (!courseUserService.existsByUserId(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(COURSE_USER_NOT_FOUND);
        }
        courseUserService.deleteCourseUserByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(COURSE_USER_DELETED_SUCCESSFULLY);
    }
}

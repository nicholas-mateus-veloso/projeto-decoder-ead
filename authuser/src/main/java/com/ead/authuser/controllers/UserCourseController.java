package com.ead.authuser.controllers;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.UserCourseDto;
import com.ead.authuser.dtos.services.UserCourseService;
import com.ead.authuser.dtos.services.UserService;
import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
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

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController implements UserCourseAPI {

    public static final String USER_NOT_FOUND = "User not found.";
    public static final String ERRO_SUBSCRIPTION_ALREADY_EXISTS = "Erro: subscription already exists!";
    public static final String USER_COURSE_NOT_FOUND = "UserCourse not found.";
    public static final String USER_COURSE_DELETED_SUCCESSFULLY = "UserCourse deleted successfully.";

    private final CourseClient courseClient;

    private final UserService userService;

    private final UserCourseService userCourseService;

    public UserCourseController(CourseClient courseClient,
                                UserService userService,
                                UserCourseService userCourseService) {
        this.courseClient = courseClient;
        this.userService = userService;
        this.userCourseService = userCourseService;
    }

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(@PageableDefault(
            page = 0,
            size = 10,
            sort = "courseId",
            direction = Sort.Direction.ASC) Pageable pageable,
                                                               @PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllCoursesByUser(userId, pageable));
    }

    @PostMapping("/users/{userId}/courses/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "userId") UUID userId,
                                                               @RequestBody @Valid UserCourseDto userCourseDto) {
        Optional<UserModel> userModelOptinal = userService.findById(userId);
        if (!userModelOptinal.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        if (userCourseService.existsByUserAndCourseId(userModelOptinal.get(), userCourseDto.getCourseId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ERRO_SUBSCRIPTION_ALREADY_EXISTS);
        }

        UserCourseModel userCourseModel = userCourseService.save(userModelOptinal
                .get()
                .convertToUserCourseModel(userCourseDto.getCourseId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(userCourseModel);
    }

    @DeleteMapping("/users/courses/{courseId}")
    public ResponseEntity<Object> deleteUserCourseByCourse(@PathVariable(value = "courseId") UUID courseId) {
        if (!userCourseService.existsByCourseId(courseId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_COURSE_NOT_FOUND);
        }
        userCourseService.deleteUserCourseByCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(USER_COURSE_DELETED_SUCCESSFULLY);
    }
}
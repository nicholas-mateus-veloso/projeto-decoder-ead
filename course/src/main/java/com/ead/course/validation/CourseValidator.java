package com.ead.course.validation;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.dtos.CourseDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.enums.UserType;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class CourseValidator implements Validator {

    public static final String MSG_ERROR_USERTYPE_INSTRUCTOR_OR_ADMIN = "User must be INSTRUCTOR or ADMIN.";
    public static final String MSG_INSTRUCTOR_NOT_FOUND = "Instructor not found.";
    public static final String USER_INSTRUCTOR_FIELD = "userInstructor";
    public static final String USER_INSTRUCTOR_ERROR_CODE = "UserInstructorError";
    private final Validator validator;

    private final AuthUserClient authUserClient;

    public CourseValidator(@Qualifier("defaultValidator") Validator validator,
                           AuthUserClient authUserClient) {
        this.validator = validator;
        this.authUserClient = authUserClient;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseDto courseDto = (CourseDto) target;
        validator.validate(courseDto, errors);
        if (!errors.hasErrors()) {
            validateUserInstructor(courseDto.getUserInstructor(), errors);
        }
    }

    private void validateUserInstructor(UUID userInstructor, Errors erros) {
        ResponseEntity<UserDto> responseUserInstructor;
        try {
            responseUserInstructor = authUserClient.getOneUserById(userInstructor);
            if (responseUserInstructor.getBody().getUserType().equals(UserType.STUDENT)) {
                erros.rejectValue(
                        USER_INSTRUCTOR_FIELD,
                        USER_INSTRUCTOR_ERROR_CODE,
                        MSG_ERROR_USERTYPE_INSTRUCTOR_OR_ADMIN
                );
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                erros.rejectValue(
                        USER_INSTRUCTOR_FIELD,
                        USER_INSTRUCTOR_ERROR_CODE,
                        MSG_INSTRUCTOR_NOT_FOUND
                );
            }
        }
    }

}
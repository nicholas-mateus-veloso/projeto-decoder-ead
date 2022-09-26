package com.ead.course.validation;

import com.ead.course.dtos.CourseDto;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseValidator implements Validator {

    private final Validator validator;

    public CourseValidator(@Qualifier("defaultValidator") Validator validator) {
        this.validator = validator;
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
//        ResponseEntity<UserDto> responseUserInstructor;
//        try {
//            responseUserInstructor = authUserClient.getOneUserById(userInstructor);
//            if (responseUserInstructor.getBody().getUserType().equals(UserType.STUDENT)) {
//                erros.rejectValue(
//                        USER_INSTRUCTOR_FIELD,
//                        USER_INSTRUCTOR_ERROR_CODE,
//                        MSG_ERROR_USERTYPE_INSTRUCTOR_OR_ADMIN
//                );
//            }
//        } catch (HttpStatusCodeException e) {
//            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
//                erros.rejectValue(
//                        USER_INSTRUCTOR_FIELD,
//                        USER_INSTRUCTOR_ERROR_CODE,
//                        MSG_INSTRUCTOR_NOT_FOUND
//                );
//            }
//        }
    }

}
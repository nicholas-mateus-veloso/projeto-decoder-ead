package com.ead.course.services.impl;

import com.ead.course.services.UtilsService;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements UtilsService {

    public String createUrlGetAllUsersByCourse(UUID courseId, Pageable pageable) {
        return "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber() + "&size=" +
                pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replace(": ", ",");
    }

    @Override
    public String getOneUserById(UUID userId) {
        return "/users/" + userId;
    }

    @Override
    public String createUrlPostSubscriptionUserInCourse(UUID userId) {
        return "/users/" + userId + "/courses/subscription";
    }

    @Override
    public String createUrlDeleteCourseInAuthUser(UUID courseId) {
        return "/users/courses/" + courseId;
    }
}
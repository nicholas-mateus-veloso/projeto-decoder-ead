package com.ead.authuser.services.impl;

import com.ead.authuser.services.UtilsService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Value("{ead.api.url.course}")
    private String requestUriCourse;

    public String createUrl(UUID userId, Pageable pageable) {
        return requestUriCourse + "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size=" +
                pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }
}

package com.ead.authuser.dtos.services;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface UtilsService {

    String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable);
}

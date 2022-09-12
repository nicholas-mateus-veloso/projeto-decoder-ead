package com.ead.authuser.repositories;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);
}
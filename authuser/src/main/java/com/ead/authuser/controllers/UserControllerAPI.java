package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.specifications.SpecificationTemplate;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserControllerAPI {

    ResponseEntity<Page<UserModel>> getAllUsers(SpecificationTemplate.UserSpec spec, Pageable pageable, UUID courseId);

    ResponseEntity<Object> getOneUser(UUID userId);

    ResponseEntity<Object> deleteUser(UUID userId);

    ResponseEntity<Object> updateUser(UUID userId, UserDto userDto);

    ResponseEntity<Object> updatePassword(UUID userId, UserDto userDto);

    ResponseEntity<Object> updateImage(UUID userId, UserDto userDto);
}

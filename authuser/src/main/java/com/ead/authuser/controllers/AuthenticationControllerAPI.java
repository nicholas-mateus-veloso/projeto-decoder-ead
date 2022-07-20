package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationControllerAPI {

    ResponseEntity<Object> registerUser(UserDto userDto);

}

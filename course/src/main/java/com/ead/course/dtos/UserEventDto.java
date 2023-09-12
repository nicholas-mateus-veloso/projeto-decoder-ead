package com.ead.course.dtos;

import com.ead.course.models.UserModel;
import java.util.UUID;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserEventDto {

    private UUID userId;
    private String userName;
    private String email;
    private String fullName;
    private String userStatus;
    private String userType;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;
    private String actionType;

    public UserModel convertToUserModel() {
        var userModel = new UserModel();
        BeanUtils.copyProperties(this, userModel);
        return userModel;
    }

}
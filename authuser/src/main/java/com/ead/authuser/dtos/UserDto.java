package com.ead.authuser.dtos;

import com.ead.authuser.validation.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface UserView {
        public static interface RegistrationPost {
        }

        public static interface UserPut {
        }

        public static interface PassowrdPut {
        }

        public static interface ImagePut {
        }
    }

    private UUID userId;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
    @UsernameConstraint(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String username;

    @Email(groups = UserView.RegistrationPost.class)
    @NotBlank(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PassowrdPut.class})
    @Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PassowrdPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.PassowrdPut.class})
    private String password;

    @NotBlank(groups = UserView.PassowrdPut.class)
    @Size(min = 6, max = 20, groups = UserView.PassowrdPut.class)
    @JsonView(UserView.PassowrdPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @CPF(groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView({UserView.RegistrationPost.class, UserView.ImagePut.class})
    private String imageUrl;

}
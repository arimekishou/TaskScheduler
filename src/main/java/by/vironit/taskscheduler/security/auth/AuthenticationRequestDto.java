package by.vironit.taskscheduler.security.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequestDto {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

}

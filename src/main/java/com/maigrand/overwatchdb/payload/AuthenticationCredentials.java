package com.maigrand.overwatchdb.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthenticationCredentials {

    @Email(message = "{email.valid}")
    @NotBlank(message = "{email.not_blank}")
    private String email;

    @NotBlank(message = "{password.not_blank}")
    private String password;

    private boolean rememberMe;
}

package com.natali.cultickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPostDto {

    @JsonProperty("id")
    private int id;

    @NotNull
    @JsonProperty("userName")
    @Size(min = 5, max = 30, message = "*Your user name must have from 5 to 30 characters")
    private String userName;

    @NotNull
    @Email
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    @Size(min = 7, max = 30, message = "*Your password must have from 7 to 30 characters")
    private String password;

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @JsonProperty("lastName")
    private String lastName;
}

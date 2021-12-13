package com.natali.cultickets.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    @Length(min = 5, max = 30, message = "*Your user name must have from 5 to 30 characters")
    @NotEmpty(message = "*Please provide a user name")
    private String userName;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @NotEmpty(message = "*Please provide your name")
    private String firstName;
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
    private Boolean active;
    private Set<Role> roles;
}

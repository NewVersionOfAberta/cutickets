package com.natali.cultickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.natali.cultickets.model.Role;
import java.util.Set;
import lombok.Data;

@Data
public class UserDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("roles")
    private Set<Role> roles;

}

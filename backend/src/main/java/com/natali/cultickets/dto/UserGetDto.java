package com.natali.cultickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserGetDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;
}

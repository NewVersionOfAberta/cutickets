package com.natali.cultickets.dto;

import com.natali.cultickets.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDto {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private Boolean active;
    private Set<Role> roles;
}

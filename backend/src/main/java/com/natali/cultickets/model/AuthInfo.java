package com.natali.cultickets.model;

import lombok.Data;

@Data
public class AuthInfo {
    private int id;
    private int userId;
    private String login;
    private String passwordHash;
    private String salt;
}

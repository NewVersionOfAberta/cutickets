package com.natali.cultickets.model;

import lombok.Data;

@Data
public class AuthInfo {
    String login;
    String passwordHash;
    String salt;
}

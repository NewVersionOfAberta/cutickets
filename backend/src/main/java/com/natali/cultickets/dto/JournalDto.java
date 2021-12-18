package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class JournalDto {
    private int id;
    private String login;
    private String userName;
    private String time;
    private String table;
    private String column;
    private String value;
    private String operation;
}

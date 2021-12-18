package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    private int id;
    private String login;
    private String userName;
    private String time;
    private String table;
    private String column;
    private String value;
    private String operation;
}

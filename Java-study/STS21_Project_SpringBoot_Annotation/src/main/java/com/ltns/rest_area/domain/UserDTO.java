package com.ltns.rest_area.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {

    private long um_uid;
    private String um_username;
    private String um_password;
    private String passwordCheck;
    private String um_nickname;
    private Timestamp um_regdate;
    private char um_enabled;
    private String message;
}

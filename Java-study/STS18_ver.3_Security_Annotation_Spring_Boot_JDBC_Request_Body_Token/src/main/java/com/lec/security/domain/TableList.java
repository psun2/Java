package com.lec.security.domain;

import lombok.Data;

@Data
public class TableList {

    private String username;
    private String password;
    private String nickname;
    private char enabled;
    private String authority;

}

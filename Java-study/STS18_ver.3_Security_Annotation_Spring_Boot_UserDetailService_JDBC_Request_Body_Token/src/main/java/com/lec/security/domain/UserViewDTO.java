package com.lec.security.domain;

import lombok.Data;

@Data
public class UserViewDTO {

    private String username;
    private String nickname;
    private char enabled;
    private String authority;

}

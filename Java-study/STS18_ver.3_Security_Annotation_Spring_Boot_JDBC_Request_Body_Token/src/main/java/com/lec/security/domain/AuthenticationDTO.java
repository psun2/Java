package com.lec.security.domain;

import lombok.Data;

@Data
public class AuthenticationDTO {

    private String username;
    private String authority;

}

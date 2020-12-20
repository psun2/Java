package com.ltns.rest_area.domain;

import lombok.Data;

@Data
public class AuthDTO {

    private long uid;
    private String authority;
    private String message;

}

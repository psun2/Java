package com.ltns.rest_area.domain;

import lombok.Data;

@Data
public class UserAuthDTO {

    private long uid;
    private String username;
    private String authority;
    private String addAuthority;
    private String prevAuthority;
    private String nextAuthority;
    private String message;

}

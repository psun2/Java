package com.ltns.rest_area.domain.user;

import com.ltns.rest_area.domain.DTO;
import lombok.Data;

@Data
public class AuthDTO implements DTO {

    private long um_uid;
    private String authority;
    private String message;

}

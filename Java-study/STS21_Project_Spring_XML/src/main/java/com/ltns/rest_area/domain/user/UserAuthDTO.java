package com.ltns.rest_area.domain.user;

import com.ltns.rest_area.domain.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
public class UserAuthDTO implements DTO {

    private long uid;
    private String username;
    private String password;
    private String nickname;
    private char enabled;
    private String regdate;
    private String authority;
    private String addAuthority;
    private String prevAuthority;
    private String nextAuthority;
    private String message;

    public void setRegdate(Timestamp regdate) {
        this.regdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regdate);
    }

}

package com.ltns.rest_area.domain.user;

import com.ltns.rest_area.domain.DTO;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
public class UserDTO implements DTO {

    private long um_uid;
    private String um_username;
    private String um_password;
    private String passwordCheck;
    private String um_nickname;
    private String um_regdate;
    private char um_enabled;
    private String message;

    public void setUm_regdate(Timestamp um_regdate) {
        this.um_regdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(um_regdate);
    }


}

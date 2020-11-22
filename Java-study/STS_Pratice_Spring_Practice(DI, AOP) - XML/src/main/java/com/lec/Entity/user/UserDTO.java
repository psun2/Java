package com.lec.Entity.user;

import com.lec.Entity.DTO;
import lombok.Data;

@Data
public class UserDTO implements DTO {

    private long uid;
    private String userId;
    private String userPassword;

}

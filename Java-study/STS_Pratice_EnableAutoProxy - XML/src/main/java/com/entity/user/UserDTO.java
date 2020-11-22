package com.entity.user;

import com.entity.DTO;
import lombok.Data;

@Data
public class UserDTO implements DTO {

    private long sequence;
    private String userId;
    private String userPassword;

}

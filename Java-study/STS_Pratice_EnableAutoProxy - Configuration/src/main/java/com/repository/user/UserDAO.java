package com.repository.user;

import com.entity.DTO;
import com.entity.user.UserDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
public class UserDAO implements DTO {

    long SEQUENCE = 0;

    @Autowired
    @Qualifier("userDTO")
    private UserDTO userDTO;

    public void insert(String userId, String userPassword) {

        System.out.println("[insert 전] : " + userDTO);
        
        userDTO.setSequence(++this.SEQUENCE);
        userDTO.setUserId(userId);
        userDTO.setUserPassword(userPassword);
        
        System.out.println("[insert 후] : " + userDTO);
    }

}

package com.lec.repository.user;

import com.lec.Entity.user.UserDTO;
import com.lec.repository.DAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
public class UserDAO implements DAO {

    @Autowired
    @Qualifier("userDTO")
    private UserDTO dto;

    public int insert(String userId, String userPassword) {

        try {
            dto.setUid(1);
            dto.setUserId(userId);
            dto.setUserPassword(userPassword);
            System.out.println(dto);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // 데이터베이스 오류
    }

}

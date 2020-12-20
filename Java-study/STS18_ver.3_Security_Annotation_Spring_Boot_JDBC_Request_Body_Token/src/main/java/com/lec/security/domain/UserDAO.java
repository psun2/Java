package com.lec.security.domain;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    @Select("SELECT username, nickname FROM userMember WHERE username = #{username}")
    UserDTO selectUser(UserDTO user);

}

package com.lec.security.domain;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    @Select("SELECT username, nickname FROM userMember WHERE username = #{username}")
    UserDTO selectUser(UserDTO user);

    // UserDetailsService 와  UserDetails 에 필요한 쿼리문
    // (마이바티스 내에서 이루어지지만 쿼리는 security의 규칫을 따러야 합니다.)
    @Select("SELECT username, password FROM userMember WHERE username = #{username}")
    UserDTO findByUid(String username);


}

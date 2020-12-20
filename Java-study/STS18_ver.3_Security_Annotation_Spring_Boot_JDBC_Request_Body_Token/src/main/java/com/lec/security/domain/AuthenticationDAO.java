package com.lec.security.domain;

import org.apache.ibatis.annotations.Select;

public interface AuthenticationDAO {

    @Select("SELECT username, authority FROM auth WHERE username = #{username}")
    AuthenticationDTO selectAuth(UserDTO user);

}

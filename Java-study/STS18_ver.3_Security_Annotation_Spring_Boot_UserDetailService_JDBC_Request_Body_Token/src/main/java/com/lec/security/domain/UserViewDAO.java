package com.lec.security.domain;


import org.apache.ibatis.annotations.Select;

public interface UserViewDAO {

    @Select("SELECT username, nickname, enabled, authority FROM userView WHERE username = #{username}")
    AuthenticationDTO selectAuth(UserDTO user);

}

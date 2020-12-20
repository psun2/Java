package com.ltns.rest_area.service.user;

import com.ltns.rest_area.domain.user.UserDTO;

import java.util.List;

public interface UserSrvice {

    List<UserDTO> findAll();

    List<UserDTO> findSelector(UserDTO user);  // 유저고유값, 유저아이디, 닉네임 을 반영한 select 동적 쿼리

    int insertUser(UserDTO user);

    int updateSelector(UserDTO user); // nickname password 을 반영한 update 동적 쿼리

    int deleteByUesrname(UserDTO user);
}

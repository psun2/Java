package com.lec.security.service;

import com.lec.security.domain.UserDAO;
import com.lec.security.domain.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    UserDAO userDAO;

    // @MapperScan 으로 인한 더이상 session은 필요 없습니다.
    // @Autowired
    // SqlSession sqlSession;

    public UserDTO getUser(UserDTO user) {
        return userDAO.selectUser(user);
//        return sqlSession.getMapper(UserDAO.class).selectUser(user);
    }
}

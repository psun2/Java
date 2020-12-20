package com.lec.security.service;

import com.lec.security.config.security.config.CustomUserDetails;
import com.lec.security.domain.UserDAO;
import com.lec.security.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserDTO> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public CustomUserDetails findByUserDetails(String username) {
        return userDAO.findByUserDetails(username);
    }
}

package com.lec.security.service;

import com.lec.security.config.security.config.CustomUserDetails;
import com.lec.security.domain.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> findByUsername(String username);

    public CustomUserDetails findByUserDetails(String username);

}

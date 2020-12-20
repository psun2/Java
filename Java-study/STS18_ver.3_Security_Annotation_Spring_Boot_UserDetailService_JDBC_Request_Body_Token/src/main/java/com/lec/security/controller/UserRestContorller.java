package com.lec.security.controller;

import com.lec.security.domain.UserDTO;
import com.lec.security.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserRestContorller {

    @Autowired
    UserServiceImpl service;

    @PostMapping("/userinfo")
    public UserDTO getUser(@RequestBody UserDTO user) {
        log.info("parameter user : {}", user);
        UserDTO uu = service.getUser(user);
        log.info("return user : {}", uu);
        return uu;
    }
}

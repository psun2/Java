package com.lec.spring.config;

import com.lec.Entity.user.UserDTO;
import com.lec.repository.user.UserDAO;
import com.lec.spring.aop.UserAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class UserConfig {

    @Bean
    public UserAspect userAspect() {
        return new UserAspect();
    }

    @Bean
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }


}

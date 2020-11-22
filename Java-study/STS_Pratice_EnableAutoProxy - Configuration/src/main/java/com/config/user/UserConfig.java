package com.config.user;

import com.aspect.user.UserAspect;
import com.config.Config;
import com.entity.user.UserDTO;
import com.repository.user.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // 설정 파일임을 명시
@EnableAspectJAutoProxy // auto - proxy 생성
public class UserConfig implements Config {

    @Bean
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public UserAspect userAspect() {
        return new UserAspect();
    }

}

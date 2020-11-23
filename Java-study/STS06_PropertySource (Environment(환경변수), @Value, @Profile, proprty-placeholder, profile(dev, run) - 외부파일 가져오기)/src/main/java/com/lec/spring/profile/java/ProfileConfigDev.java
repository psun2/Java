package com.lec.spring.profile.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ProfileConfigDev {

    @Bean
    public ServerInfo serverInfo() {
        return new ServerInfo("localhost", "8080");
    }

}

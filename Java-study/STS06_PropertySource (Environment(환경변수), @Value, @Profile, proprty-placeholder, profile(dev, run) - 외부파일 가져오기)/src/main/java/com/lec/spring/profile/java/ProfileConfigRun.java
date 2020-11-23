package com.lec.spring.profile.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("run")
public class ProfileConfigRun {

    @Bean
    public ServerInfo serverInfo() {
        return new ServerInfo("192.168.0.1", "80");
    }
}

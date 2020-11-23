package com.lec.spring.lifecycle.java;

import com.lec.spring.Score;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoreConfig {

    @Bean
    public Score score() {
        return new Score(100, 100, 100, "올백 좋아요!");
    }

}

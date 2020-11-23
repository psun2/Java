package com.lec.spring.scope.java;

import com.lec.spring.Score;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScoreConfig {

    @Bean
    @Scope("singleton")
    public Score score1() {
     return new Score(55, 66, 23, "이것도 점수?");
    }

    @Bean
    @Scope("prototype")
    public Score score2() {
     return new Score(100, 80, 90, "참 잘했어요");
    }

}

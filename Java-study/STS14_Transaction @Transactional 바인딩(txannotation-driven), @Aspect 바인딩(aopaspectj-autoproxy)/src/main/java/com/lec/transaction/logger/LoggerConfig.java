package com.lec.transaction.logger;

import com.lec.transaction.controller.TickController;
import com.lec.transaction.domain.TicketDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(TicketDAO.class);
    }

    @Bean
    public Logger controllerLogger() {
        return LoggerFactory.getLogger(TickController.class);
    }
}

package com.lec.jdbc_refactoring.config;

import com.lec.jdbc_refactoring.controller.BoardController;
import com.lec.jdbc_refactoring.domain.WriteDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
public class Config {

    @Bean
    public Logger loggerController() {
        return LoggerFactory.getLogger(BoardController.class);
    }

    @Bean
    public Logger loggerDAO() {
        return LoggerFactory.getLogger(WriteDAOImpl.class);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        driverManagerDataSource.setUsername("scott_b");
        driverManagerDataSource.setPassword("tiger_b");

        System.out.println(driverManagerDataSource.toString());

        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

}

package com.lec.transaction.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JDBCConfig {

    @Bean
    public DataSource dataSource() { // DataSource Bean 생성
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        driverManagerDataSource.setUsername("scott_b");
        driverManagerDataSource.setPassword("tiger_b");

        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() { // spring jdbc JdbcTemplate 객체 Bean 생성
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    // dao2
    // PlatformTransactionManager
    // DataSource 객체 의존
    public PlatformTransactionManager platformTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    // dao3
    // TransactionTemplate
    // PlatformTransactionManager 객체 의존
    // DataSource 객체 의존
    public TransactionTemplate transactionTagSupport() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(platformTransactionManager());
        return transactionTemplate;
    }

}

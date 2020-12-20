package com.ltns.rest_area.config.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
public class JbdcConfig {

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean // 외부 환경파일 setup
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("static/common/db.info"));
        log.info("PropertySourcesPlaceholderConfigurer Bean 등록(외부설정파일 긁어 오기): {}", propertySourcesPlaceholderConfigurer);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public DBInfo dbInfo() {
        return new DBInfo();
    }

    @Bean // Data connection 셋팅
    public DataSource dataSource() {
        // 맴버 변수에서 외부 환경파일을 가져다 쓰거나,

        // Bean 을통해 주입받은 맴버 변수로 셋팅된 Bean 을 사용 할 수 있습니다.
        DBInfo dbInfo = dbInfo();

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        log.info("DBInfo 외부설정파일로 설정된 Bean: {}", dbInfo.toString());
        log.info("DataSource Bean 등록: {}", driverManagerDataSource);
        return driverManagerDataSource;
    }

    @Bean // JdbcTemplate 를 통해 직접 JDBC를 사용합니다.
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean // TransactionTemplate 사용을 위한 dataSourceTransactionManager 생성
    public PlatformTransactionManager platformTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean // TransactionTemplate으로 @Transactional 어노테이션 작동
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(platformTransactionManager());
        return transactionTemplate;
    }
}

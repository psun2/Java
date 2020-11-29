package com.lec.mybatis.config;


import com.lec.mybatis.domain.AA;
import com.lec.mybatis.domain.BoardDAO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("com.lec.generatedkey.domain")
public class MybatisConfig {

    @Value("${db.driverClassName}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
    private String driverClassName;

    @Value("${db.url}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
    private String url;

    @Value("${db.username}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
    private String username;

    @Value("${db.password}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
    private String password;

    @Bean
    // <context:property-placeholder location=".., .."/> 과 대응 합니다.
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // static 키워드가 붙어야 제대로 동작합니다.
        // static 키워드가 없을시 null

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("common/db.info"));

        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(this.driverClassName);
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.username);
        driverManagerDataSource.setPassword(this.password);

        return driverManagerDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSession sqlSession() {
        // MapperFactoryBean<BoardDAO> boardDAOMapperFactoryBean = new MapperFactoryBean<>(BoardDAO.class); // 한개의 설정파일만 가능
        MapperFactoryBean boardDAOMapperFactoryBean = new MapperFactoryBean<>(); // 여러개의 설정 파일 생성시
        // 아마도 session 객체라서... 한개 초과되는 session을 만들면 자꾸 에러가 생깁니다.
        boardDAOMapperFactoryBean.setMapperInterface(BoardDAO.class); // 동작 완료
        boardDAOMapperFactoryBean.setMapperInterface(AA.class); // 동작완료
        try {
            boardDAOMapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean().getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardDAOMapperFactoryBean.getSqlSession();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }
}

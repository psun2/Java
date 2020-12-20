package com.lec.security.jdbc;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("com.lec.security.domain")
// @MapperScan("com.lec.security.domain.*") //  No MyBatis mapper was found in '[com.lec.security.domain.*]' package. Please check your configuration.
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    // 스프링 부트에서 진행시 에는
    // @MapperScan("com.lec.security.domain.mybatis") 이 자동적으로 맵핑을 해주므로
    // sqlsession을 따로 반환 할 필요가 없습니다.
//    @Bean
//    public SqlSession sqlSession() {
//        // MapperFactoryBean<UserDAO> objectMapperFactoryBean = new MapperFactoryBean<>(UserDAO.class);
//         MapperFactoryBean objectMapperFactoryBean = new MapperFactoryBean<>();
//        objectMapperFactoryBean.setMapperInterface(MemberDAO.class);
//        return objectMapperFactoryBean.getSqlSession();
//    }
}

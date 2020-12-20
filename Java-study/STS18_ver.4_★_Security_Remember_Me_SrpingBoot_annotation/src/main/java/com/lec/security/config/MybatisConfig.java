package com.lec.security.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.lec.security.domain")
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean // SqlSessionFactoryBean 빈
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    // 부트로 넘어오면서 자동적으로 Sqlsession에 바인딩 되어 만들 필요는 없지만
    // 주석으로 남기기 위하여 Session 생성법을 남기도록 하겠습니다.
//    @Bean
//    public SqlSession sqlSession() {
//        // MapperFactoryBean<UserDAO> objectMapperFactoryBean = new MapperFactoryBean<>(UserDAO.class);
//         MapperFactoryBean objectMapperFactoryBean = new MapperFactoryBean<>();
//        objectMapperFactoryBean.setMapperInterface(MemberDAO.class);
//        return objectMapperFactoryBean.getSqlSession();
//    }
}

package com.ltns.rest_area.config.mybatis;

import com.ltns.rest_area.domain.UserDAO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.ltns.rest_area.domain")
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean // 마이바티스 의 핵심인 sqlSessionFactoryBean 생성
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

//    @Bean // SqlSession 반환
//    // Spring에서는 필수 설정 이지만,
//    // Spring boot 에서는 자동적으로 맵핑 해줍니다.
//    public SqlSession sqlSession() {
//        // MapperFactoryBean<UserDAO> mapperFactoryBean = new MapperFactoryBean<>(UserDAO.class);
//        MapperFactoryBean objectMapperFactoryBean = new MapperFactoryBean<>();
//        objectMapperFactoryBean.setMapperInterface(UserDAO.class);
//        return objectMapperFactoryBean.getSqlSession();
//    }
}

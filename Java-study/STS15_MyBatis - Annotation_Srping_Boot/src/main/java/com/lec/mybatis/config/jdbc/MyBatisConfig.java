package com.lec.mybatis.config.jdbc;

import com.lec.mybatis.domain.TestDAO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.lec.mybatis.domain")
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    // 스프링 엔진에서는 sqlSession 객체를 바인딩 시켜서 내보내줘야하지만
    // boot 는 그러한 역할을 자동적으로 수행해 필요 없게 됩니다.
//    @Bean
//    public SqlSession mapperFactoryBean() {
//        // 여러 클래쓰는 방법
//        MapperFactoryBean<> mapperFactoryBean = new MapperFactoryBean<>();
//        mapperFactoryBean.setMapperInterface(TestDAO.class);
//        return mapperFactoryBean.getSqlSession();
//
//        // 한가지 클래스를 사용
//        MapperFactoryBean<TestDAO> testDAOMapperFactoryBean = new MapperFactoryBean<>();
//        return testDAOMapperFactoryBean.getSqlSession();
//    }
}

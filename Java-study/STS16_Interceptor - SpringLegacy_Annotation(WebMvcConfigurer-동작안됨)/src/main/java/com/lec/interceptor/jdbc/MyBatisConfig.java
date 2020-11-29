package com.lec.interceptor.jdbc;

import com.lec.interceptor.domain.BoardDAO;
import com.lec.interceptor.domain.TicketDAO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.lec.interceptor.domain")
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    // DataSession 생성을 위한 SessionFactory 객체 등록
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource);
        return sqlSessionFactoryBean;
    }

    // SqlSessionTemplate 객체 등록 (부모 인터페이스인 Sqlsession)
    @Bean
    public SqlSession sqlSession() {
        MapperFactoryBean objectMapperFactoryBean = new MapperFactoryBean<>();
        try {
            objectMapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean().getObject());
            objectMapperFactoryBean.setMapperInterface(BoardDAO.class);
            objectMapperFactoryBean.setMapperInterface(TicketDAO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectMapperFactoryBean.getSqlSession();
    }
}

package com.lec.generatedkey.config;

import com.lec.generatedkey.domain.AA;
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
public class AAConfig {

//    @Value("${db.driverClassName}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
//    private String driverClassName;
//
//    @Value("${db.url}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
//    private String url;
//
//    @Value("${db.username}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
//    private String username;
//
//    @Value("${db.password}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
//    private String password;
//
//    @Bean
//    // <context:property-placeholder location=".., .."/> 과 대응 합니다.
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer2() {
//        // static 키워드가 붙어야 제대로 동작합니다.
//        // static 키워드가 없을시 null
//
//        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("common/db.info"));
//
//        return propertySourcesPlaceholderConfigurer;
//    }
//
//    @Bean
//    public DataSource dataSource2() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName(this.driverClassName);
//        driverManagerDataSource.setUrl(this.url);
//        driverManagerDataSource.setUsername(this.username);
//        driverManagerDataSource.setPassword(this.password);
//
//        return driverManagerDataSource;
//    }
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean2() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource2());
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    // 중첩된 예외로써 사용 불가
//    public SqlSession sqlSession2() { // 중첩된 예외로써 사용 불가
//        MapperFactoryBean<AA> boardDAOMapperFactoryBean = new MapperFactoryBean<>(AA.class);
//        try {
//            boardDAOMapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean2().getObject());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return boardDAOMapperFactoryBean.getSqlSession();
//    }
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager2() {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource2());
//        return dataSourceTransactionManager;
//    }

}

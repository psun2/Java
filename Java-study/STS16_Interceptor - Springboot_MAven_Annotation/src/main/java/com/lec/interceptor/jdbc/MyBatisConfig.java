package com.lec.interceptor.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 파일임을 명시하고
@MapperScan("com.lec.interceptor.domain") // Mapper을 스캔한다면
// 더이상 sqlSession 을 Interface에 Mappoing 할 필요가 없습니다.
public class MyBatisConfig {
    // spring boot 에서는 더이상
    // 어노테이션 마이바티스 Mapping을 따로 해주지 않아도 동작합니다.
    // MybatisAutoConfiguration.class 에서 자동적으로 Mapping 을 시켜줍니다.

//    @Autowired
//    @Qualifier("dataSource")
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    public SqlSession sqlSession() {
//        MapperFactoryBean objectMapperFactoryBean = new MapperFactoryBean<>();
//        try {
//            objectMapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean().getObject());
//            objectMapperFactoryBean.setMapperInterface(BoardDAO.class);
//            objectMapperFactoryBean.setMapperInterface(TicketDAO.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return objectMapperFactoryBean.getSqlSession();
//    }

}

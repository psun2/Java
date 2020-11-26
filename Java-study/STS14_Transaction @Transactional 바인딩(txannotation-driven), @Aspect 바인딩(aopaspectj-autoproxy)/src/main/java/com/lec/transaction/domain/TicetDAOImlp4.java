package com.lec.transaction.domain;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Qualifier("dao4") // 트랜잭션 사용 @Transactional
// @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor={Exception.class}) // 클래스 단으로 Transaction 처리합니다.
// @Transactinal 설정
// 1. xml 설정 (servlet-context.xml) 참조
// 2. 어노테이션 설정
// @Configuration 즉 설정파일에서
// @EnableTransactionManagement
// 위 역할은 아마도 @EnableAspectJAutoProxy 와같이
// @Transactional 에 PlatformTransactionManager 을 binding 합니다.
// <tx:annotation-driven transaction-manager="transactionManager"/>
// EnableAspectJAutoProxy => auto proxy 를 생성헤 @Aspect에 바인딩 합니다.
// <aop:aspectj-autoproxy /> => aop에 오토프록시를 바인딩
public class TicetDAOImlp4 implements TicketDAO {

    @Autowired
    @Qualifier("logger")
    private Logger logger;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class}) // 메소드 단 Transaction 처리 합니다.
    // @Transactinal 설정
    // 1. xml 설정 (servlet-context.xml) 참조
    // 2. 어노테이션 설정
    // @Configuration 즉 설정파일에서
    // @EnableTransactionManagement
    // 위 역할은 아마도 @EnableAspectJAutoProxy 와같이
    // @Transactional 에 PlatformTransactionManager 을 binding 합니다.
    // <tx:annotation-driven transaction-manager="transactionManager"/>
    // EnableAspectJAutoProxy => auto proxy 를 생성헤 @Aspect에 바인딩 합니다.
    // <aop:aspectj-autoproxy /> => aop에 오토프록시를 바인딩
    public Object createBuy(TicketDTO dto) {
        logger.info("buyTicket");
        logger.info("user id: {}", dto.getUserId());
        logger.info("ticket count: {}", dto.getTicketCount());

        // 요구사항: 공연사 발권은 5장 이상 하지 못 합니다.
        // 카드사 결제 후 -> 공연사 발권 진행

        // 카드사 결재
        int cardCompany = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO test_card VALUES (?, ?)";
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, dto.getUserId());
                psmt.setInt(2, dto.getTicketCount());
                return psmt;
            }
        });

        logger.info("cardCompany(카드사 결제): {} => [성공1, 실패0]", cardCompany);

        // 공연사 발권
        int onetsaniCompany = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO test_ticket VALUES(?, ?)";
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, dto.getUserId());
                psmt.setInt(2, dto.getTicketCount());
                return psmt;
            }
        });

        logger.info("onetsaniCompany(공연사 발권): {} => [성공1, 실패0]", onetsaniCompany);

        logger.info("dao1의 트랜잭션의 문제점은 공연사 발권 트랜잭션이 처리 되지 않았지만, 카드사 결제 트랜잭션은 통과해서 돈만 빠져나간 셈이 되었습니다.");

        return onetsaniCompany;
    }

    @Override
    public void buyTicket(TicketDTO dto) {
    }
}

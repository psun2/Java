package com.lec.transaction.domain;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Qualifier("dao1") // 트랜잭션 미적용
public class TicetDAOImlp1 implements TicketDAO {

    @Autowired
    @Qualifier("logger")
    private Logger logger;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
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

package com.lec.transaction.domain;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.PreparedStatement;

@Component
@Qualifier("dao2")  // 트랜잭션 사용 PlatformTransactionManager
public class TicetDAOImlp2 implements TicketDAO {

    @Autowired
    @Qualifier("logger")
    private Logger logger;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 트랜잭션 처리 객체 (인터페이스)
    // PlatformTransactionManager (I)
    ///// └─ AbstractPlatformTransactionManager (추상클래스)
    ////////// └─ ... 다양한 클래스 (DB 종류, 환경에 따른 객체들이 정의되어 있다)
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public Object createBuy(TicketDTO dto) {
        logger.info("buyTicket");
        logger.info("user id: {}", dto.getUserId());
        logger.info("ticket count: {}", dto.getTicketCount());

        // PlatformTransactionManager 와 TransactionStatus 사용
        // try catch 로 전부를 감싸주고
        // try 가 됐을시와 catch 시 로직을 구분하여 작성합니다.
        // platformTransactionManager.commit(transactionStatus);
        // platformTransactionManager.rollback(transactionStatus);

        // 사용방법
        // TransactionDefinition : 트랜잭션 구동을 위한 기본객체, (기본설정값을 가지고 있습니다.)
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(definition);

        try {

            // 요구사항: 공연사 발권은 5장 이상 하지 못 합니다.
            // 카드사 결제 후 -> 공연사 발권 진행

            // 카드사 결재
            int cardCompany = jdbcTemplate.update(con -> {
                String sql = "INSERT INTO test_card VALUES (?, ?)";
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, dto.getUserId());
                psmt.setInt(2, dto.getTicketCount());
                return psmt;
            });

            logger.info("cardCompany(카드사 결제): {} => [성공1, 실패0]", cardCompany);

            // 공연사 발권
            int onetsaniCompany = jdbcTemplate.update(con -> {
                String sql = "INSERT INTO test_ticket VALUES(?, ?)";
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, dto.getUserId());
                psmt.setInt(2, dto.getTicketCount());
                return psmt;
            });

            logger.info("onetsaniCompany(공연사 발권): {} => [성공1, 실패0]", onetsaniCompany);

            logger.info("dao1의 트랜잭션의 문제점은 공연사 발권 트랜잭션이 처리 되지 않았지만, 카드사 결제 트랜잭션은 통과해서 돈만 빠져나간 셈이 되었습니다.");

            // 트랜잭션 성공 --> commit()
            platformTransactionManager.commit(transactionStatus);

            return onetsaniCompany;
        } catch (Exception e) {
            // 트랜잭션 실패 --> rollback()
            platformTransactionManager.rollback(transactionStatus);
            logger.error("트랜잭션 실패: {}", e.getMessage());
            throw e;
        }

    }

    @Override
    public void buyTicket(TicketDTO dto) {
    }
}

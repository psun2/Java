package com.lec.sts14_transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Qualifier("dao2") // 트랜잭션 사용 PlatformTransactionManager
public class TicketDAOImpl2 implements TicketDAO {

	public final static Logger logger = LoggerFactory.getLogger(TicketDAOImpl2.class);

	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// 트랜잭션 처리 객체 (인터페이스)
	// PlatformTransactionManager (I)
	///// └─ AbstractPlatformTransactionManager (추상클래스)
	////////// └─ ... 다양한 클래스 (DB 종류, 환경에 따른 객체들이 정의되어 있다)

	PlatformTransactionManager transactionManager;

	@Autowired
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	// 티켓 구매
	@Override
	public void buyTicket(TicketDTO dto) {
		logger.info("buyTicket");
		logger.info("user id: {}", dto.getUserId());
		logger.info("ticket count: {}", dto.getTicketCount());

		// 요구사항: 공연사 발권은 5장 이상 하지 못 합니다.
		// 카드사 결제 후 -> 공연사 발권 진행

		// 사용방법
		// TransactionDefinition : 트랜잭션 구동을 위한 기본객체, (기본설정값을 가지고 있습니다.)
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);

		try {
			
		// 카드사 결재
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO test_card VALUES (?, ?)";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, dto.getUserId());
				psmt.setInt(2, dto.getTicketCount());
				return psmt;
			}
		});

		// 공연사 발권
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO test_ticket VALUES(?, ?)";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, dto.getUserId());
				psmt.setInt(2, dto.getTicketCount());
				return psmt;
			}
		});
		
		// 트랜잭션 성공 --> commit()
		transactionManager.commit(status);
		
		} catch(Exception e) {
			logger.error("트랜잭션 실패: {}", e.getMessage());
			// 트랜잭션 실패 --> rollback()
			transactionManager.rollback(status);
			throw e;
		}
	}

}

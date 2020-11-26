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
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Qualifier("dao3") // 트랜잭션 사용 TransactionTemplate 
public class TicketDAOImpl3 implements TicketDAO {

	public final static Logger logger = LoggerFactory.getLogger(TicketDAOImpl3.class);

	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// TransactionTemplate 사용
	private TransactionTemplate transactionTemplate;

	@Autowired
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	// 티켓 구매
	@Override
	public void buyTicket(TicketDTO dto) {
		logger.info("buyTicket");
		logger.info("user id: {}", dto.getUserId());
		logger.info("ticket count: {}", dto.getTicketCount());

		// 요구사항: 공연사 발권은 5장 이상 하지 못 합니다.
		// 카드사 결제 후 -> 공연사 발권 진행

		// TransactionTemplate 객체 사용
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
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
			}
		});

	}

}

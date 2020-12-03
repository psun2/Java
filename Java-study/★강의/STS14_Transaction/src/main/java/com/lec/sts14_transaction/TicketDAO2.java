package com.lec.sts14_transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
@Qualifier("dao2")
public class TicketDAO2 implements TicketDAO {

	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	// 트랜잭션 처리 객체 (인터페이스)
	// PlatformTransactionManager (I)
	//   └─ AbstractPlatformTransactionManager (추상클래스)
	//        └─  ...  다양한 클래스 (DB 종류, 환경에 따른 객체들이 정의되어 있다)
	
	PlatformTransactionManager transactionManager;
	@Autowired
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	// 티켓 구매
	@Override
	public void buyTicket(final TicketDTO dto) {
		System.out.println("buyTicket()");
		System.out.println("user id : " + dto.getUserId());
		System.out.println("ticket count : " + dto.getTicketCount());
		
		// 사용방법
		// TransactionDefinition : 트랜잭션 구동을 위한 기본객체 (기본설정값 갖고 있다)
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			
			// 카드사 결재
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "INSERT INTO test_card VALUES (?, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getUserId());
					pstmt.setInt(2, dto.getTicketCount());
					return pstmt;
				}
			});
			
			// 공연사 발권
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "INSERT INTO test_ticket VALUES (?, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getUserId());
					pstmt.setInt(2, dto.getTicketCount());
					return pstmt;
				}
			});
			
			// 트랜잭션 성공 --> commit()
			transactionManager.commit(status);
			
		} catch(Exception e) {
			// 트랜잭션 실패 --> rollback()
			transactionManager.rollback(status);
			throw e;
		}
		
	}
	
	
}









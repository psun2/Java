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
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Qualifier("dao3")
public class TicketDAO3 implements TicketDAO {

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
	public void buyTicket(final TicketDTO dto) {
		System.out.println("buyTicket()");
		System.out.println("user id : " + dto.getUserId());
		System.out.println("ticket count : " + dto.getTicketCount());
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
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
			}
		});
			
			
			
		
	}
	
	
}









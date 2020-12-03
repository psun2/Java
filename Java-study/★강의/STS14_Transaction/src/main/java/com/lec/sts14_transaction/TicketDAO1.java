package com.lec.sts14_transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dao1")
public class TicketDAO1 implements TicketDAO {

	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	// 티켓 구매
	@Override
	public void buyTicket(final TicketDTO dto) {
		System.out.println("buyTicket()");
		System.out.println("user id : " + dto.getUserId());
		System.out.println("ticket count : " + dto.getTicketCount());
		
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
	} // end buyTicket()
	
} // end DAO









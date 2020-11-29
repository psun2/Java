package com.lec.sts15_mybatis.ticket;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TicketService {
	TicketDAO dao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	

	public void buyTicket(final TicketDTO dto) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao = sqlSession.getMapper(TicketDAO.class);
				dao.insertCard(dto.getUserId(), dto.getTicketCount());
				dao.insertTicket(dto.getUserId(), dto.getTicketCount());
			}
		});
		
	}
	
	

}

















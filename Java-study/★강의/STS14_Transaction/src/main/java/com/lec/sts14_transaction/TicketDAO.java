package com.lec.sts14_transaction;

import org.springframework.transaction.annotation.Transactional;

public interface TicketDAO {

	// 티켓 구매
	void buyTicket(TicketDTO dto);

}
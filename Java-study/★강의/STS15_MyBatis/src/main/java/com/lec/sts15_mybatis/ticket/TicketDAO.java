package com.lec.sts15_mybatis.ticket;

public interface TicketDAO {
	int insertCard(String userId, int buyAmount);
	int insertTicket(String userId, int ticketCount);
}

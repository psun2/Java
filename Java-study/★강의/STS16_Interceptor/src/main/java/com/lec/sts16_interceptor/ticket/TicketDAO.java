package com.lec.sts16_interceptor.ticket;

public interface TicketDAO {
	int insertCard(String userId, int buyAmount);
	int insertTicket(String userId, int ticketCount);
}

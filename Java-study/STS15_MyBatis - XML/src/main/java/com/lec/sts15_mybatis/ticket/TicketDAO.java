package com.lec.sts15_mybatis.ticket;

public interface TicketDAO {

    int insertCard(String userId, int buyAmount);
    int insertTicket(String userId, int ticketCount);

    int insertCard2(String userId, int buyAmount);
    int insertTicket2(String userId, int ticketCount);

    int insertCard3(String userId, int buyAmount);
    int insertTicket3(String userId, int ticketCount);

}

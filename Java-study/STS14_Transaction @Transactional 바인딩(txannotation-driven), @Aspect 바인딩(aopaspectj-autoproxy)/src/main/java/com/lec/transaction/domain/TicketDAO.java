package com.lec.transaction.domain;

public interface TicketDAO {

    // 티켓 구매
    Object createBuy(TicketDTO dto);

    // 티켓 구매
    void buyTicket(TicketDTO dto);

}

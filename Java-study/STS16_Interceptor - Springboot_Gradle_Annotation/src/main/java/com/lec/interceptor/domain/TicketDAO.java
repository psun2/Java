package com.lec.interceptor.domain;

import org.apache.ibatis.annotations.Insert;

public interface TicketDAO {
    @Insert("INSERT INTO test_card VALUES(#{param1}, #{param2})")
    int insertCard(String userId, int buyAmount);

    @Insert("INSERT INTO test_ticket VALUES(#{param1}, #{param2})")
    // @Options(useGeneratedKeys = true, keyColumn = "Primary key 없음", keyProperty = "DTO에 없음")
    int insertTicket(String userId, int ticketCount);
}

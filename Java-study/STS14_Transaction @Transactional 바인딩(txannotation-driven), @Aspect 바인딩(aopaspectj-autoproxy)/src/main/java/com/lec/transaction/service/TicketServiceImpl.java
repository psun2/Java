package com.lec.transaction.service;

import com.lec.transaction.domain.TicketDAO;
import com.lec.transaction.domain.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TicketServiceImpl {

    @Autowired
    // @Qualifier("dao1")  // 트랜잭션 미적용
    // @Qualifier("dao2") // 트랜잭션 사용 PlatformTransactionManager
    // @Qualifier("dao3") // 트랜잭션 사용 TransactionTemplate
    @Qualifier("dao4") // 트랜잭션 사용 @Transactional
    private TicketDAO dao;

    public Object createBuy(TicketDTO dto) {
        try {
            dao.createBuy(dto);
            return dto;
        } catch (Exception e) {
            return "fail";
        }
    }
}

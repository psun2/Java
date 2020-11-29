package com.lec.sts15_mybatis.ticket;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Slf4j
public class TicketService {

    TicketDAO dao;

    @Autowired
    private SqlSession sqlSession;

    // 트랜잭션 관리 1
    @Autowired
    TransactionTemplate transactionTemplate;

    // 트랜잭션 관리 2
    @Autowired
    PlatformTransactionManager platformTransactionManager;

    // 1. TranscationTemplate 객체사용
    public void buyTicket(final TicketDTO dto) {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                sqlSession.getMapper(TicketDAO.class).insertCard(dto.getUserId(), dto.getTicketCount());

                sqlSession.getMapper(TicketDAO.class).insertTicket(dto.getUserId(), dto.getTicketCount());

            }
        });

    }

    // 2. DataSourceTransactionManager 사용
    public void buyTicket2(TicketDTO dto) {

        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transaction = platformTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            sqlSession.getMapper(TicketDAO.class).insertCard(dto.getUserId(), dto.getTicketCount());

            sqlSession.getMapper(TicketDAO.class).insertTicket(dto.getUserId(), dto.getTicketCount());

            platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            log.error("에러발생 롤백합니다. {}", e.getMessage());
            platformTransactionManager.rollback(transaction);
        }
    }

    // 3. @Transaction 사용
    @Transactional
    public void buyTicket3(TicketDTO dto) {
        sqlSession.getMapper(TicketDAO.class).insertCard(dto.getUserId(), dto.getTicketCount());

        sqlSession.getMapper(TicketDAO.class).insertTicket(dto.getUserId(), dto.getTicketCount());
    }

}

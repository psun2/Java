package com.lec.interceptor.service;

import com.lec.interceptor.domain.TicketDAO;
import com.lec.interceptor.domain.TicketDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void buyTicket(final TicketDTO dto) {
        sqlSession.getMapper(TicketDAO.class).insertCard(dto.getUserId(), dto.getTicketCount());
        sqlSession.getMapper(TicketDAO.class).insertTicket(dto.getUserId(), dto.getTicketCount());
    }

}

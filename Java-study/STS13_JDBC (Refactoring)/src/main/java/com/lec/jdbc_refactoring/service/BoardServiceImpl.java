package com.lec.jdbc_refactoring.service;

import com.lec.jdbc_refactoring.domain.WriteDAO;
import com.lec.jdbc_refactoring.domain.WriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private WriteDAO dao;

    @Override
    public List<WriteDTO> readAll() {
        return dao.readAll();
    }

    @Override
    public int create(WriteDTO dto) {
        return dao.create(dto);
    }

    @Override
    public List<WriteDTO> readyAndViewcntIncrease(long uid) {
        return dao.readyAndViewcntIncrease(uid);
    }

    @Override
    public List<WriteDTO> readByUid(long uid) {
        return dao.readByUid(uid);
    }

    @Override
    public int update(WriteDTO dto) {
        return dao.update(dto);
    }

    @Override
    public int delete(long uid) {
        return dao.delete(uid);
    }
}

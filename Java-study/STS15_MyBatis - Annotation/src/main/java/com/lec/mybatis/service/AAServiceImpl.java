package com.lec.mybatis.service;


import com.lec.mybatis.domain.AA;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AAServiceImpl implements AAService {

    @Autowired
    @Qualifier("sqlSession")
    SqlSession sqlSession;

    @Override
    public int selectCard() {
        return sqlSession.getMapper(AA.class).selectCard();
    }
}

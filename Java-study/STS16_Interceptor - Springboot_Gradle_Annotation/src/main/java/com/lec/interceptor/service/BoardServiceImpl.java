package com.lec.interceptor.service;

import com.lec.interceptor.domain.BoardDAO;
import com.lec.interceptor.domain.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<BoardDTO> getListAll() {
        return sqlSession.getMapper(BoardDAO.class).getListAll();
    }

    @Override
    public int write(BoardDTO dto) {

        int result = sqlSession.getMapper(BoardDAO.class).write(dto);

        log.info("Annotation: 생성된 uid 는 {}", dto.getUid());

        return result;
    }

    @Override
    public List<BoardDTO> viewByUid(long uid) {

        // 조회수 증가
        sqlSession.getMapper(BoardDAO.class).viewCnt(uid);

        // 조회수 를 증가된 상태로 view return
        return sqlSession.getMapper(BoardDAO.class).selectByUid(uid);
    }

    @Override
    public List<BoardDTO> selectByUid(long uid) {

        // update 를 위한 view
        return sqlSession.getMapper(BoardDAO.class).selectByUid(uid);
    }

    @Override
    public int update(BoardDTO dto) {
        return sqlSession.getMapper(BoardDAO.class).update(dto);
    }

    @Override
    public int deleteByUid(long uid) {
        return sqlSession.getMapper(BoardDAO.class).deleteByUid(uid);
    }
}

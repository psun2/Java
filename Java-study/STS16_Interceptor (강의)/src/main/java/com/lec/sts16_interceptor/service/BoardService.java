package com.lec.sts16_interceptor.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.sts16_interceptor.domain.WriteDAO;
import com.lec.sts16_interceptor.domain.WriteDTO;

@Service
public class BoardService {

	WriteDAO dao;
	
	// MyBatis
	private SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public BoardService() {
		super();
		System.out.println("BoardService() 생성");
	}

	public List<WriteDTO> list() {
		dao = sqlSession.getMapper(WriteDAO.class);  // MyBatis 사용
		return dao.select();
	}

	public int write(WriteDTO dto) {
		dao = sqlSession.getMapper(WriteDAO.class);
		int result = dao.insert(dto);
		//return dao.insert(dto.getSubject(), dto.getContent(), dto.getName());
		
		System.out.println("생성된 uid 는 " + dto.getUid());
		
		
		return result;
	}

	public List<WriteDTO> viewByUid(int uid) {
		dao = sqlSession.getMapper(WriteDAO.class); // MyBatis 사용
		dao.incViewCnt(uid);
		return dao.selectByUid(uid);
	}

	public List<WriteDTO> selectByUid(int uid) {
		dao = sqlSession.getMapper(WriteDAO.class); // MyBatis 사용
		return dao.selectByUid(uid);  // 1개짜리 List
	}

	public int update(WriteDTO dto) {
		dao = sqlSession.getMapper(WriteDAO.class); // MyBatis 사용
		//return dao.update(dto);
		return dao.update(dto.getUid(), dto);
	}

	public int deleteByUid(int uid) {
		dao = sqlSession.getMapper(WriteDAO.class); // MyBatis 사용
		return dao.deleteByUid(uid);
	}
	
	//
	
	
}











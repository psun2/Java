package com.lec.sts19_rest.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lec.sts19_rest.domain.AjaxDAO;
import com.lec.sts19_rest.domain.WriteDTO;

@Service
@Transactional
public class AjaxService {

	@Autowired
	private SqlSession sqlSession;

	public List<WriteDTO> list(int from, int pageRows) {
		return sqlSession.getMapper(AjaxDAO.class) //
				.selectFromRow(from, pageRows);
	}

	public int count() {
		return sqlSession.getMapper(AjaxDAO.class) //
				.countAll();
	}

	public List<WriteDTO> viewByUid(int uid) {
		// ※ 트랜잭션 처리해야 한다.
		sqlSession.getMapper(AjaxDAO.class) //
				.incViewCnt(uid);
		return sqlSession.getMapper(AjaxDAO.class) //
				.selectByUid(uid);
	}

	public List<WriteDTO> selectByUid(int uid) {
		return sqlSession.getMapper(AjaxDAO.class) //
				.selectByUid(uid);
	}

	public int write(WriteDTO dto) {
		return sqlSession.getMapper(AjaxDAO.class) //
				.insert(dto);
	}

	public int update(WriteDTO dto) {
		return sqlSession.getMapper(AjaxDAO.class) //
				.update(dto);
	}

	public int delete(int[] uids) {
		return sqlSession.getMapper(AjaxDAO.class) //
				.deleteByUid(uids);
	}

}

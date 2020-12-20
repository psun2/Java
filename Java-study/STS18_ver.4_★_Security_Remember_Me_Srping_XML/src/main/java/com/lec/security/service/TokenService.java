package com.lec.security.service;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.lec.security.domain.TokenDAO;
import com.lec.security.domain.TokenVO;

public class TokenService {

	@Autowired
	private SqlSession sqlSession;

	public int createNewToken(TokenVO tokenVo) {
		return sqlSession.getMapper(TokenDAO.class).createNewToken(tokenVo);
	};

	public int updateToken(String series, String tokenValue, Date lastUsed) {
		return sqlSession.getMapper(TokenDAO.class).updateToken(series, tokenValue, lastUsed);
	};

	public TokenVO getTokenForSeries(String seriesId) {
		return sqlSession.getMapper(TokenDAO.class).getTokenForSeries(seriesId);
	};

	public int removeUserTokens(String username) {
		return sqlSession.getMapper(TokenDAO.class).removeUserTokens(username);
	};

}

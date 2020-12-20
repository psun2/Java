package com.lec.security.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.lec.security.config.CustomUserDetails;
import com.lec.security.domain.DAO;
import com.lec.security.domain.DTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DAOService {

	@Autowired
	private SqlSession sqlSession;

	public DAOService() {
		log.info("DAOService 빈 등록");
		log.info("SqlSession 주입 : {}", sqlSession);
	}

	public List<DTO> findByUsername(String username) {
		log.info("DAOService findByUsername({}) : {}", username,
				sqlSession.getMapper(DAO.class).findByUsername(username));
		return sqlSession.getMapper(DAO.class).findByUsername(username);
	};

	public CustomUserDetails findByUserDetail(String username) {
		return sqlSession.getMapper(DAO.class).findByUserDetail(username);
	}

}

package com.ltns.rest_area.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.restarea.RestAreaCDTO;
import com.ltns.rest_area.domain.restarea.RestAreaDAO;

@Service
public class SearchService {
	
	DAO dao;

	@Autowired
	private SqlSession sqlSession;

	public int raCount() {
		dao=sqlSession.getMapper(RestAreaDAO.class);
		return dao.selectCnt();
	}

	public List<DTO> selectSomeRaDTOs(String routeName, String destination, String orderBy, int from, int numOfRows) {
		dao=sqlSession.getMapper(RestAreaDAO.class);
		return dao.selectByDTO(new RestAreaCDTO().builder().routeName(routeName).destination(destination).orderBy(orderBy).from(from).numOfRows(numOfRows).build());
	}

	public int gsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<DTO> selectSomeGsDTOs(String routeName, String destination, String orderBy, int from, int numOfRows) {
		// TODO Auto-generated method stub
		return null;
	}

	public int fmCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<DTO> selectSomeFmDTOs(String routeName, String destination, String orderBy, int from, int numOfRows) {
		// TODO Auto-generated method stub
		return null;
	}


	
}

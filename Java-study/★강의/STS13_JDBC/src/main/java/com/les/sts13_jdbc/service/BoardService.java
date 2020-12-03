package com.les.sts13_jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.les.sts13_jdbc.domain.WriteDAO;
import com.les.sts13_jdbc.domain.WriteDTO;

@Service
public class BoardService {

	WriteDAO dao;
	@Autowired
	public void setDao(WriteDAO dao) {
		this.dao = dao;
	}
	
	public BoardService() {
		super();
		System.out.println("BoardService() 생성");
	}

	public List<WriteDTO> list() {
		return dao.select();
	}

	public int write(WriteDTO dto) {
		return dao.insert(dto);
	}

	public List<WriteDTO> viewByUid(int uid) {
		return dao.readByUid(uid);
	}

	public List<WriteDTO> selectByUid(int uid) {
		return dao.selectByUid(uid);
	}

	public int update(WriteDTO dto) {
		return dao.update(dto);
	}

	public int deleteByUid(int uid) {
		return dao.deleteByUid(uid);
	}
	
	//
	
	
}











package com.ltns.rest_area.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.ScheduleDAO;
import com.ltns.rest_area.domain.admin.ScheduleDTO;

@Service
public class ScheduleService {

	@Autowired
	private SqlSession sqlSession;
	DAO dao;
	
	
	
	//달력 서비스 view 
	public List<DTO> showSchedule()  {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		return dao.selectAll();
	}
	
	//달력서비스 insert 
	public int addSchedule(String subject, String startdate, String enddate, String memo) {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		
	
		return dao.inset_(subject, startdate, enddate, memo);
	}
	
	// delete 
	public int deleteSchedule(String subject) {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		return dao.deleteByString(subject);
	}
	
	// update 
	
	public int updateSchedule(String add_subject, String subject, String startdate, String enddate, String memo) {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		
		
		return dao.update_(add_subject, subject, startdate, enddate, memo);
	}

	
	

		
}

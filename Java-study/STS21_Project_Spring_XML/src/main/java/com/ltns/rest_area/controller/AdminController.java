package com.ltns.rest_area.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ltns.rest_area.service.ScheduleService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	String subject;
	String startdate;
	String enddate;
	String memo;
	String addSubject;
	
	
	@Autowired
	ScheduleService service;

	
	
	@RequestMapping("/dashboard")
	public void dashboard() {}
	
	
	
	
	
	
	//일정 
	@PostMapping("/schedule")
	@ResponseBody
	public void addSchedule(@RequestBody Map<String, Object> datas) {
	
		for (Map.Entry<String, Object> data: datas.entrySet()) {
			
			if(data.getKey().equals("subject")) {
				subject = (String) data.getValue();
			}else if( data.getKey().equals("startDate")) {
				startdate = (String) data.getValue();
			}else if( data.getKey().equals("endDate")) {
				enddate = (String) data.getValue();
			}else {
				memo = (String) data.getValue();
			}
		}
	
		service.addSchedule(subject, startdate, enddate, memo);
	
	}
	
	//delete 
	@DeleteMapping("/schedule")
	@ResponseBody
	public void deleteSchedule(@RequestBody Map<String, Object> datas) {
	
		for (Map.Entry<String, Object> data: datas.entrySet()) {
			
			if(data.getKey().equals("subject")) {
				subject = (String) data.getValue();
			}
		}
	
		service.deleteSchedule(subject);
	
	}
	
	
	//update PUT
	@PutMapping("/schedule")
	@ResponseBody
	public void updateSchedule(@RequestBody Map<String, Object> datas) {
	
		for (Map.Entry<String, Object> data: datas.entrySet()) {
			
			if(data.getKey().equals("subject")) {
				subject = (String) data.getValue();
			}else if( data.getKey().equals("startDate")) {
				startdate = (String) data.getValue();
			}else if( data.getKey().equals("endDate")) {
				enddate = (String) data.getValue();
			}else if( data.getKey().equals("add_subject")){
				addSubject = (String) data.getValue();
			}else {
				memo = (String) data.getValue();
			}
		}
	
		System.out.println(addSubject + subject + startdate + enddate + memo);
		service.updateSchedule(addSubject, subject, startdate, enddate, memo);
	
	}
	
	
	@RequestMapping("/schedule")
	public void schedule(Model m) throws Exception
	{
		m.addAttribute("showSchedule", service.showSchedule());
		
	}
	
	
	
	@RequestMapping("/schedulePopup")
	public void Popup()	{}
	
	@RequestMapping("/deletePopup")
	public void deletePopup()	{}
	
	@RequestMapping("/updatePopup")
	public void updatePopup()	{}
	
	
	
	
}

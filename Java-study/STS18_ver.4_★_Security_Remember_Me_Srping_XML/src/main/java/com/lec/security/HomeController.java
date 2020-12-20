package com.lec.security;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lec.security.domain.DAO;
import com.lec.security.service.DAOService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {


	@Autowired
	DataSource dataSource;
	
	@Autowired
	DAOService daoService;

	@Autowired
	SqlSession session;

	public HomeController() {
		logger.info("HomeController 빈 등록");
		logger.info("HomeController DataSource 빈 주입 : {}", dataSource);
		logger.info("HomeController DAOService 빈 주입 : {}", daoService);
		logger.info("HomeController SqlSession 빈 주입 : {}", session); 
	}



	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		logger.info("{}", dataSource);

		logger.info("{}", daoService);
		
		logger.info("{}", session);
		
		logger.info("{}", session.getMapper(DAO.class));
		
		String username = "admin";

		logger.info("{}", session.getMapper(DAO.class).findByUsername(username));
		
		logger.info("DAOService 주입 테스트 : {}", daoService.findByUsername("admin"));

		return "home";
	}

}

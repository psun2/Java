package com.lec.sts14_transaction;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private TicketDAO dao;

	@Autowired
	// @Qualifier("dao1") // 트랜잭션 미적용
	// @Qualifier("dao2") // 트랜잭션 사용 PlatformTransactionManager
	// @Qualifier("dao3") // 트랜잭션 사용 TransactionTemplate 
	@Qualifier("dao4") // buyTicket 메소드 자체를 transaction으로 관리 합니다.
	public void setDao(TicketDAO dao) {
		this.dao = dao;
	}

	// 티켓 구매 양식 (폼)
	@GetMapping("/buy_ticket")
	public void buyTicket() {
	}

	// 티켓 구매 처리 (트랜잭션)
	@PostMapping("/buy_ticket_card")
	public String buyTicketCard(TicketDTO dto, Model model) {
		logger.info("/buy_ticket_card");
		logger.info("user id: {}", dto.getUserId());
		logger.info("ticket count: {}", dto.getTicketCount());

		String page = "bye_ticket_done";

		try {
			dao.buyTicket(dto); // 트랜잭션!
			model.addAttribute("ticketInfo", dto);
		} catch (Exception e) {
			e.printStackTrace();
			page = "buy_ticket_fail"; // 트랜잭션 오류 발생시..
		}

		return page;

	}

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

		return "home";
	}

}

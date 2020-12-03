package com.lec.sts15_mybatis.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@RequestMapping("/buy_ticket")
	public void buy_ticket() {}
	
	@RequestMapping("/buy_ticket_card")
	public String buyTicketCard(TicketDTO dto, Model model) {
		System.out.println( "/buy_ticket_card" );
		System.out.println( "user id : " + dto.getUserId());
		System.out.println( "ticket count : " + dto.getTicketCount());
		
		String page = "ticket/buy_ticket_done";  // 트랜잭션 성공시
		
		try {
			ticketService.buyTicket(dto);  // 트랜잭션!
			model.addAttribute("ticketInfo", dto);
		} catch(Exception e) {
			e.printStackTrace();
			page = "ticket/buy_ticket_fail";   // 트랜잭션 오류 발생시..
		}		
		
		return page;
	}
	
	
}




















package com.lec.sts15_mybatis.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    // 티켓 구매 양식 (폼)
    @GetMapping("/buy_ticket")
    public void buyTicket() {
    }

    // 티켓 구매 처리 (트랜잭션)
    @PostMapping("/buy_ticket_card")
    public String buyTicketCard(Model model, @org.jetbrains.annotations.NotNull TicketDTO dto) {
        logger.info("/buy_ticket_card");
        logger.info("user id: {}", dto.getUserId());
        logger.info("ticket count: {}", dto.getTicketCount());

        String page = "ticket/buy_ticket_done";

        try {
            ticketService.buyTicket(dto); // 트랜잭션!
            ticketService.buyTicket2(dto); // 트랜잭션!
            ticketService.buyTicket3(dto); // 트랜잭션!
            model.addAttribute("ticketInfo", dto);
        } catch (Exception e) {
            e.printStackTrace();
            page = "ticket/buy_ticket_fail"; // 트랜잭션 오류 발생시..
        }

        return page;

    }

}

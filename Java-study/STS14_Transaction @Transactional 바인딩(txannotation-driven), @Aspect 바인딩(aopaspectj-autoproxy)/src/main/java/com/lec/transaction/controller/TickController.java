package com.lec.transaction.controller;

import com.lec.transaction.domain.TicketDTO;
import com.lec.transaction.service.TicketServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TickController {

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    @Qualifier("controllerLogger")
    Logger logger;

    // 티켓 구매 양식 (폼)
    @GetMapping("/buy_ticket")
    public void buyTicket() {
    }

    // 티켓 구매 처리 (트랜잭션)
    @PostMapping("/buy_ticket_card")
    public String buyTicketCard(TicketDTO dto, Model model) {

        String page = null;

        Object obj = ticketService.createBuy(dto);

        if (obj.getClass().getSimpleName().equals("String")) {
            page = "buy_ticket_fail"; // 트랜잭션 오류 페이지
        } else {
            page = "bye_ticket_done"; // 트랜잭션 성공 페이지

            model.addAttribute("ticketInfo", dto);
        }

        return page;
    }
}

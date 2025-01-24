package com.capstone.hanperform.ticket.controller;

import com.capstone.hanperform.performances.dto.PerformancesResponseDto;
import com.capstone.hanperform.ticket.dto.TicketDto;
import com.capstone.hanperform.ticket.service.TicketService;
import com.capstone.hanperform.users.dto.UserDto;
import com.capstone.hanperform.users.entity.User;
import com.capstone.hanperform.users.service.UserService;
import com.capstone.hanperform.performances.entity.Performances;
import com.capstone.hanperform.performances.service.PerformancesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final PerformancesService performancesService;

    @PostMapping("/reserve/{performanceId}")
    public String reserveTicket(@PathVariable Long performanceId,
                                @ModelAttribute TicketDto ticketDto,
                               ) {
//        UserDto user = userService.getUserByName(principal.getName());
        PerformancesResponseDto performance = performancesService.getPerformanceById(performanceId);

        ticketService.createTicket(performance.getId(), ticketDto);

        return "redirect:/tickets/reservations"; // 예매 목록 페이지로 이동
    }

    @GetMapping("/reservations")
    public String reservations(Model model, Principal principal) {
//        UserDto user = userService.getUserByName(principal.getName());
        List<TicketDto> tickets = ticketService.getAllTickets();

        model.addAttribute("tickets", tickets);
        return "reservations"; // reservations.html 렌더링
    }

    // 티켓 삭제
    @GetMapping("/delete/{ticketId}")
    public String deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return "redirect:/tickets/reservations";
    }
}

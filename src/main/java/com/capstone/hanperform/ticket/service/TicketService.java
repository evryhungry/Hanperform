package com.capstone.hanperform.ticket.service;

import com.capstone.hanperform.performances.entity.Performances;
import com.capstone.hanperform.performances.repository.PerformancesRepository;
import com.capstone.hanperform.ticket.dto.TicketDto;
import com.capstone.hanperform.ticket.entity.Ticket;
import com.capstone.hanperform.ticket.repository.TicketRepository;
import com.capstone.hanperform.users.entity.User;
import com.capstone.hanperform.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final PerformancesRepository performancesRepository;

    public TicketDto createTicket(Long performanceId, TicketDto ticketDTO) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        Performances performance = performancesRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 존재하지 않습니다."));

        int seatNumber = ticketDTO.getSeatNumber();
        if (seatNumber < 1 || seatNumber > 20) {
            throw new IllegalArgumentException("좌석 번호는 1부터 20까지 가능합니다.");
        }

        boolean isSeatTaken = ticketRepository.existsByPerformanceIdAndSeatNumber(performanceId, seatNumber);
        if (isSeatTaken) {
            throw new IllegalArgumentException("해당 좌석은 이미 예약되었습니다.");
        }

        Ticket ticket = new Ticket(performance, ticketDTO.getDate(), seatNumber);
        Ticket savedTicket = ticketRepository.save(ticket);
        return TicketDto.fromEntity(savedTicket);
    }
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketDto::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
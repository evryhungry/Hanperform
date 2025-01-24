package com.capstone.hanperform.ticket.dto;

import com.capstone.hanperform.performances.entity.Performances;
import com.capstone.hanperform.ticket.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    private int seatNumber;
    private LocalDate date;
    private Performances performance;

    public static TicketDto fromEntity(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .seatNumber(ticket.getSeatNumber())
                .date(ticket.getDate())
                .performance(ticket.getPerformance())
                .build();
    }
}
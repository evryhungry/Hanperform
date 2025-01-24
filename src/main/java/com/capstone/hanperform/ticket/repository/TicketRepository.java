package com.capstone.hanperform.ticket.repository;

import com.capstone.hanperform.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByPerformanceId(Long performanceId);
    boolean existsByPerformanceIdAndDateAndSeatNumber(Long performanceId, LocalDate date, int seatNumber);
}
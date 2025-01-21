package com.capstone.hanperform.service;

import com.capstone.hanperform.dto.SeatRequestDto;
import com.capstone.hanperform.entity.Seat;
import com.capstone.hanperform.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public void createSeat(SeatRequestDto seatRequestDto, ) {
        seatRepository.save(Seat.toEntity(seatRequestDto));
    }
}

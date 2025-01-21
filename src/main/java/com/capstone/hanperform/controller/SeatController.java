package com.capstone.hanperform.controller;


import com.capstone.hanperform.dto.SeatRequestDto;
import com.capstone.hanperform.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/seat")
public class SeatController {
    private final SeatService seatService;


    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("{performanceId}")
    public ResponseEntity<String> createSeat(@RequestBody SeatRequestDto dto, @PathVariable Long performanceId) {
        try {
            seatService.createSeat(dto);
            return ResponseEntity.ok("Seat created successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}

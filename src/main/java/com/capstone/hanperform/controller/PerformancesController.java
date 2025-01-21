package com.capstone.hanperform.controller;


import com.capstone.hanperform.dto.PerformancesRequestDto;
import com.capstone.hanperform.dto.PerformancesResponseDto;
import com.capstone.hanperform.service.PerformancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/performances")
public class PerformancesController {

    private final PerformancesService performancesService;

    public PerformancesController(PerformancesService performancesService) {
        this.performancesService = performancesService;
    }


    @PostMapping()
    public ResponseEntity<String> createPerformance(@RequestBody PerformancesRequestDto dto){
        try {
            performancesService.createPerformances(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Performances saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping()
    public ResponseEntity<List<PerformancesResponseDto>> getAllPerformances(){
        try{
            return ResponseEntity.ok(performancesService.getAllPerformances());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{performanceId}")
    public ResponseEntity<PerformancesResponseDto> getPerformanceById(@PathVariable Long performanceId){
        try {
            return ResponseEntity.ok(performancesService.getPerformanceById(performanceId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/{performanceId}")
    public ResponseEntity<String> updatePerformance(@PathVariable Long performanceId, @RequestBody PerformancesRequestDto dto){
        try {
            performancesService.updatePerformance(performanceId, dto);
            return ResponseEntity.status(HttpStatus.OK).body("Performances updated successfully");
        } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); }
    }

    @DeleteMapping("/{performanceId}")
    public ResponseEntity<String> deletePerformanceById(@PathVariable Long performanceId){
        try{
            performancesService.deletePerformanceById(performanceId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Performances deleted successfully");
        } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); }
    }
}

package com.capstone.hanperform.service;

import com.capstone.hanperform.dto.PerformancesRequestDto;
import com.capstone.hanperform.dto.PerformancesResponseDto;
import com.capstone.hanperform.entity.Performances;
import com.capstone.hanperform.repository.PerformancesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PerformancesService {
    private final PerformancesRepository performancesRepository;

    public void createPerformances(PerformancesRequestDto dto) {
        Performances performances = Performances.toEntity(dto);
        performancesRepository.save(performances);
    }

    public List<PerformancesResponseDto> getAllPerformances() {
        List<Performances> performances = performancesRepository.findAll();

        return performances.stream()
                .map(PerformancesResponseDto::new)
                .toList();
    }

    public PerformancesResponseDto getPerformanceById(Long id) {
        return performancesRepository.findById(id)
                .map(PerformancesResponseDto::new)
                .orElseThrow();
    }

    @Transactional
    public PerformancesResponseDto updatePerformance(Long id, PerformancesRequestDto dto) {
        Performances performances = performancesRepository.findById(id).orElseThrow();

        Performances updatedPerformances = performances.toBuilder()
                .title(dto.getTitle() != null ? dto.getTitle() : performances.getTitle())
                .description(dto.getDescription() != null ? dto.getDescription() : performances.getDescription())
                .location(dto.getLocation() != null ? dto.getLocation() : performances.getLocation())
                .startDate(dto.getStartDate() != null ? dto.getStartDate() : performances.getStartDate())
                .endDate(dto.getEndDate() != null ? dto.getEndDate() : performances.getEndDate())
                .totalSeats(dto.getTotalSeats() != null ? dto.getTotalSeats() : performances.getTotalSeats())
                .price(dto.getPrice() != null ? dto.getPrice() : performances.getPrice())
                .thumbnail(dto.getThumbnail() != null ? dto.getThumbnail() : performances.getThumbnail())
                .build();

        performancesRepository.save(updatedPerformances);

        return new PerformancesResponseDto(updatedPerformances);
    }

    @Transactional
    public void deletePerformanceById(Long id) {
        performancesRepository.deleteById(id);
    }
}

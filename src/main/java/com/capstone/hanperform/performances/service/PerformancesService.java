package com.capstone.hanperform.performances.service;

import com.capstone.hanperform.performances.dto.PerformancesRequestDto;
import com.capstone.hanperform.performances.dto.PerformancesResponseDto;
import com.capstone.hanperform.performances.entity.Performances;
import com.capstone.hanperform.performances.repository.PerformancesRepository;
import com.capstone.hanperform.photo.Poster;
import com.capstone.hanperform.photo.PosterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PerformancesService {
    private final PerformancesRepository performancesRepository;
    private final PosterService posterService;

    public Long createPerformances(PerformancesRequestDto dto, MultipartFile image) {
        Poster poster = posterService.uploadThumbnail(image);
        Performances performances = Performances.toEntity(dto, poster);
        performancesRepository.save(performances);
        return performances.getId();
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
                .price(dto.getPrice() != null ? dto.getPrice() : performances.getPrice())
                .build();

        performancesRepository.save(updatedPerformances);

        return new PerformancesResponseDto(updatedPerformances);
    }

    @Transactional
    public void updatePoster(Long id, MultipartFile image) {
        Performances performances = performancesRepository.findById(id).orElseThrow();
        Poster poster = posterService.uploadThumbnail(image);

        performances.toBuilder().poster(poster);
        performancesRepository.save(performances);
    }

    @Transactional
    public void deletePerformanceById(Long id) {
        performancesRepository.deleteById(id);
    }
}

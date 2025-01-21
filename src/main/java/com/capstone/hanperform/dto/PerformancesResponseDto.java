package com.capstone.hanperform.dto;

import com.capstone.hanperform.entity.Performances;
import lombok.Getter;

import java.util.Date;

@Getter
public class PerformancesResponseDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private Long totalSeats;
    private Long remainingSeats;
    private Long price;
    private String thumbnail;

    public PerformancesResponseDto(Performances performances) {
        this.id = performances.getId();
        this.title = performances.getTitle();
        this.description = performances.getDescription();
        this.location = performances.getLocation();
        this.startDate = performances.getStartDate();
        this.endDate = performances.getEndDate();
        this.totalSeats = performances.getTotalSeats();
        this.remainingSeats = performances.getRemainingSeats();
        this.price = performances.getPrice();
        this.thumbnail = performances.getThumbnail();
    }
}

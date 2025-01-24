package com.capstone.hanperform.performances.dto;

import com.capstone.hanperform.performances.entity.Performances;
import com.capstone.hanperform.photo.Poster;
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
    private Long price;
    private String posterUrl;

    public PerformancesResponseDto(Performances performances) {
        this.id = performances.getId();
        this.title = performances.getTitle();
        this.description = performances.getDescription();
        this.location = performances.getLocation();
        this.startDate = performances.getStartDate();
        this.endDate = performances.getEndDate();
        this.price = performances.getPrice();
        this.posterUrl = (performances.getPoster() != null) ? performances.getPoster().getImagePath() : null;
    }
}

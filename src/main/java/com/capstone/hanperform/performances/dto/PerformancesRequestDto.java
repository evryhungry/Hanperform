package com.capstone.hanperform.performances.dto;

import com.capstone.hanperform.photo.Poster;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
public class PerformancesRequestDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private Long price;
}

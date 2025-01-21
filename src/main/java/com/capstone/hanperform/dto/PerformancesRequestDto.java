package com.capstone.hanperform.dto;

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
    private Long totalSeats;
    private Long price;
    private String thumbnail;
}

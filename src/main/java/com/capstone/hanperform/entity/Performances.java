package com.capstone.hanperform.entity;


import com.capstone.hanperform.dto.PerformancesRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "performances")
public class Performances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name= "title")
    private String title;

    private String description;
    private String location;

    @Column(nullable = false , name= "start_date")
    private Date startDate;
    @Column(nullable = false, name = "end_date")
    private Date endDate;

    private Long totalSeats;
    private Long remainingSeats;

    private Long price;

    private String thumbnail;

    public static Performances toEntity(PerformancesRequestDto dto){
        return Performances.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .totalSeats(dto.getTotalSeats())
                .remainingSeats(0L)
                .price(dto.getPrice())
                .thumbnail(dto.getThumbnail())
                .build();
    }
}

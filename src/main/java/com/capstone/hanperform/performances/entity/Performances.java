package com.capstone.hanperform.performances.entity;


import com.capstone.hanperform.performances.dto.PerformancesRequestDto;
import com.capstone.hanperform.photo.Poster;
import com.capstone.hanperform.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "performance")
public class Performances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name= "title")
    private String title;

    private String description;

    private String location;

    @Column(nullable = false , name= "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(nullable = false, name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    private Long price;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "poster_id", unique = true)
    private Poster poster;

    public static Performances toEntity(PerformancesRequestDto dto, Poster poster) {
        return Performances.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .price(dto.getPrice())
                .poster(poster)
                .build();
    }

}

package com.capstone.hanperform.ticket.entity;

import com.capstone.hanperform.performances.entity.Performances;
import com.capstone.hanperform.users.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Range(min = 1, max = 20, message = "좌석 번호는 1부터 20까지 가능합니다.")
    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performances_id", referencedColumnName = "id", nullable = false)
    private Performances performance;

    public Ticket(Performances performances, LocalDate date, int seatNumber) {
        this.performance = performances;
        this.date = date;
        this.seatNumber = seatNumber;
    }
}

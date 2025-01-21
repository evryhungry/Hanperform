package com.capstone.hanperform.entity;


import com.capstone.hanperform.dto.SeatRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "performance_id")
    private Performances performances;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('AVAILABLE', 'RESERVED', 'SOLD')")
    private Status status;

    public static Seat toEntity(SeatRequestDto dto) {
        return Seat.builder()
                .performances(dto.getPerformances())
                .seatNumber(dto.getSeatNumber())
                .status(Status.AVAILABLE)
                .build();
    }
}

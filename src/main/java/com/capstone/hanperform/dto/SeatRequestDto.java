package com.capstone.hanperform.dto;


import com.capstone.hanperform.entity.Performances;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatRequestDto {
    private Performances performances;
    private String seatNumber;
}

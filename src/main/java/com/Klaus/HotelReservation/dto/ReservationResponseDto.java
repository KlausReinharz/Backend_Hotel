package com.Klaus.HotelReservation.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationResponseDto {
    private Integer totalPages;
    private Integer pageNumber;
    private List<ReservationDto> reservationDtoList;

    //esta relacionado con el metodo getReservationDto() en entity
}

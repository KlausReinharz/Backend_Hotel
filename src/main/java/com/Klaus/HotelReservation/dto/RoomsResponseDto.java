package com.Klaus.HotelReservation.dto;

import lombok.Data;

import java.util.List;

/*Se realiza la codificaicon en RoomServiceImpl*/

@Data
public class RoomsResponseDto {

    private List<RoomDto> roomDtoList;
    private Integer totalPages;
    private  Integer pageNumber;

}

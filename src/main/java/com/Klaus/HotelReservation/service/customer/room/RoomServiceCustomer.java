package com.Klaus.HotelReservation.service.customer.room;

import com.Klaus.HotelReservation.dto.RoomsResponseDto;


public interface RoomServiceCustomer {

    RoomsResponseDto getAvailableRooms(int pageNumber);
}

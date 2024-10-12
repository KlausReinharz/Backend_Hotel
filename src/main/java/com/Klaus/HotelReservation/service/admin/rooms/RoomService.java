package com.Klaus.HotelReservation.service.admin.rooms;

import com.Klaus.HotelReservation.dto.RoomDto;
import com.Klaus.HotelReservation.dto.RoomsResponseDto;

public interface RoomService {

    boolean postRoom(RoomDto roomDto);
    RoomsResponseDto getAllRooms(int pageNumber);
}

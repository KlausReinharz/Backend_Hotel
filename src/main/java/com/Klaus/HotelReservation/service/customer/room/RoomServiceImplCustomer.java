package com.Klaus.HotelReservation.service.customer.room;

import com.Klaus.HotelReservation.dto.RoomsResponseDto;
import com.Klaus.HotelReservation.entity.Room;
import com.Klaus.HotelReservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImplCustomer implements RoomServiceCustomer {

    private final RoomRepository roomRepository;

    public RoomsResponseDto getAvailableRooms(int pageNumber){
        Pageable pegable = PageRequest.of(pageNumber, 6);
        Page<Room> roomPage = roomRepository.findByAvailable(true, pegable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return  roomsResponseDto;
    }
}

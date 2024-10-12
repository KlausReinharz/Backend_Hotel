package com.Klaus.HotelReservation.service.admin.rooms;

import com.Klaus.HotelReservation.dto.RoomDto;
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
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto){
        try {
            Room room = new Room();
            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());
            room.setAvailable(true);

            roomRepository.save(room);
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    public RoomsResponseDto getAllRooms(int pageNumber){
        Pageable pegable = PageRequest.of(pageNumber, 6);
        Page<Room> roomPage = roomRepository.findAll(pegable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return  roomsResponseDto;
    }
}

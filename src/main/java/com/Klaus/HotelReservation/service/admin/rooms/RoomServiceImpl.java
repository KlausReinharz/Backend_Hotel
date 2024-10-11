package com.Klaus.HotelReservation.service.admin.rooms;

import com.Klaus.HotelReservation.dto.RoomDto;
import com.Klaus.HotelReservation.entity.Room;
import com.Klaus.HotelReservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

package com.Klaus.HotelReservation.controller.admin;

import com.Klaus.HotelReservation.dto.RoomDto;
import com.Klaus.HotelReservation.service.admin.rooms.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {
    private final RoomService roomService;

    @PostMapping("/room")
    public ResponseEntity<?>postRoom(@RequestBody RoomDto roomDto){
        boolean success = roomService.postRoom(roomDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public  ResponseEntity<?> getAllRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomService.getAllRooms(pageNumber));
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(roomService.getRoomById(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto){
        boolean success = roomService.updateRoom(id,roomDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id){
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok(null);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}

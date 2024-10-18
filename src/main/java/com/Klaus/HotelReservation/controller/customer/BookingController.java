package com.Klaus.HotelReservation.controller.customer;

import com.Klaus.HotelReservation.dto.ReservationDto;
import com.Klaus.HotelReservation.dto.ReservationResponseDto;
import com.Klaus.HotelReservation.service.customer.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?>postBooking(@RequestBody ReservationDto reservationDto){

        if (reservationDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        boolean success = bookingService.postReservation(reservationDto);

        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{usersId}/{pageNumber}")
    public ResponseEntity<?> getAllReservationByUsersId(@PathVariable Long usersId, @PathVariable int pageNumber){
        try {
            return ResponseEntity.ok(bookingService.getAllReservationByUserId(usersId, pageNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}

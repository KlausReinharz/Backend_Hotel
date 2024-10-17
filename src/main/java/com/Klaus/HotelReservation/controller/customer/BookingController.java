package com.Klaus.HotelReservation.controller.customer;

import com.Klaus.HotelReservation.dto.ReservationDto;
import com.Klaus.HotelReservation.service.customer.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

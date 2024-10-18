package com.Klaus.HotelReservation.service.customer.booking;

import com.Klaus.HotelReservation.dto.ReservationDto;
import com.Klaus.HotelReservation.dto.ReservationResponseDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);

    ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);
}

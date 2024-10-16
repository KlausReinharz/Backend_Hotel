package com.Klaus.HotelReservation.service.customer.booking;

import com.Klaus.HotelReservation.dto.ReservationDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);
}

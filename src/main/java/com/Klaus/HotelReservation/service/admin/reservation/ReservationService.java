package com.Klaus.HotelReservation.service.admin.reservation;

import com.Klaus.HotelReservation.dto.ReservationDto;
import com.Klaus.HotelReservation.dto.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto getAllReservations(int pageNumber);
    boolean changeReservationStatus(Long id, String status);
}

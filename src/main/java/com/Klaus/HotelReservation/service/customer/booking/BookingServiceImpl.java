package com.Klaus.HotelReservation.service.customer.booking;

import com.Klaus.HotelReservation.dto.ReservationDto;
import com.Klaus.HotelReservation.entity.Reservation;
import com.Klaus.HotelReservation.entity.Room;
import com.Klaus.HotelReservation.entity.Users;
import com.Klaus.HotelReservation.enums.ReservationStatus;
import com.Klaus.HotelReservation.repository.ReservationRepository;
import com.Klaus.HotelReservation.repository.RoomRepository;
import com.Klaus.HotelReservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;


    public boolean postReservation(ReservationDto reservationDto){
        Optional<Users>optionalUsers= userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        if(optionalRoom.isPresent() && optionalUsers.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUsers(optionalUsers.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice() * days);

            reservationRepository.save(reservation);
            return true;

        }
        return false;
    }

}

package com.Klaus.HotelReservation.service.customer.booking;

import com.Klaus.HotelReservation.dto.ReservationDto;
import com.Klaus.HotelReservation.dto.ReservationResponseDto;
import com.Klaus.HotelReservation.entity.Reservation;
import com.Klaus.HotelReservation.entity.Room;
import com.Klaus.HotelReservation.entity.Users;
import com.Klaus.HotelReservation.enums.ReservationStatus;
import com.Klaus.HotelReservation.repository.ReservationRepository;
import com.Klaus.HotelReservation.repository.RoomRepository;
import com.Klaus.HotelReservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 4;



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

    //metodo que se encuenta en ReservationRepository

    public ReservationResponseDto getAllReservationByUserId(Long usersId, int pageNumber){

        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage = reservationRepository.findAllByUsersId(pageable, usersId);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto)
                .collect(Collectors.toList()));

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }

}

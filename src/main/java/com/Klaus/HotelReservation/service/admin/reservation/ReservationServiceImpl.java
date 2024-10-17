package com.Klaus.HotelReservation.service.admin.reservation;

import com.Klaus.HotelReservation.dto.ReservationResponseDto;
import com.Klaus.HotelReservation.entity.Reservation;
import com.Klaus.HotelReservation.entity.Room;
import com.Klaus.HotelReservation.enums.ReservationStatus;
import com.Klaus.HotelReservation.repository.ReservationRepository;
import com.Klaus.HotelReservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 4;

    private final RoomRepository roomRepository;

    public ReservationResponseDto getAllReservations(int pageNumber){

        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto)
                .collect(Collectors.toList()));

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }

    public boolean changeReservationStatus(Long id, String status){
        Optional<Reservation>optionalReservation = reservationRepository.findById(id);

        if(optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();

            if(Objects.equals(status, "Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else{
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);

            Room existingRoom = existingReservation.getRoom();
            existingRoom.setAvailable(false);

            roomRepository.save(existingRoom);


            return true;
        }

        return false;
    }





}

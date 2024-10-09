package com.Klaus.HotelReservation.dto;

import com.Klaus.HotelReservation.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private Long userId;
    private UserRole userRole;
}

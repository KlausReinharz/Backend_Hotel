package com.Klaus.HotelReservation.service.auth;

import com.Klaus.HotelReservation.dto.SignupRequest;
import com.Klaus.HotelReservation.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}

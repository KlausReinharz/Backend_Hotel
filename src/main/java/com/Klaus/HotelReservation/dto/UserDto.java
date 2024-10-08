package com.Klaus.HotelReservation.dto;

import com.Klaus.HotelReservation.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}

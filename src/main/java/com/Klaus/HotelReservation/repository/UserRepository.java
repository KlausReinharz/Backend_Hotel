package com.Klaus.HotelReservation.repository;

import com.Klaus.HotelReservation.entity.Users;
import com.Klaus.HotelReservation.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*RECUERDA QUE USUARIO ES "USERS"*/

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findFirstByEmail(String email);
    Optional<Users> findByUserRole(UserRole userRole);
}

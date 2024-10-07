package com.Klaus.HotelReservation.service.auth;

import com.Klaus.HotelReservation.entity.Users;
import com.Klaus.HotelReservation.enums.UserRole;
import com.Klaus.HotelReservation.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    /*se menciona al repositorio userRepositorio*/
    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount(){
        Optional<Users> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            Users users = new Users();
            users.setEmail("admin@test.com");
            users.setName("Admin");
            users.setUserRole(UserRole.ADMIN);
            users.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(users);
        }else {
            System.out.println("admin account already exist");
        }
    }
}

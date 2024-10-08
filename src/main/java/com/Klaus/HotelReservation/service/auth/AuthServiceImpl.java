package com.Klaus.HotelReservation.service.auth;

import com.Klaus.HotelReservation.dto.SignupRequest;
import com.Klaus.HotelReservation.dto.UserDto;
import com.Klaus.HotelReservation.entity.Users;
import com.Klaus.HotelReservation.enums.UserRole;
import com.Klaus.HotelReservation.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
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

    public UserDto createUser(SignupRequest signupRequest){
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With email" + signupRequest.getEmail());
        }
        Users users = new Users();
        users.setEmail(signupRequest.getEmail());
        users.setName(signupRequest.getName());
        users.setUserRole(UserRole.CUSTOMER);
        users.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        Users createdUser=userRepository.save(users);
        return  createdUser.getUserDto();
    }
}

package com.renuka.backend.service;

import com.renuka.backend.dto.RegisterRequest;
import com.renuka.backend.dto.RegisterResponse;
import com.renuka.backend.entity.User;
import com.renuka.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()){

            return new RegisterResponse("Email already exists.");

        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return new RegisterResponse("User Registered Successfully.");

    }

}
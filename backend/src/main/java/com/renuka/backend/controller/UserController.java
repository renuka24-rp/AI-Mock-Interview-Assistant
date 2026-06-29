package com.renuka.backend.controller;

import com.renuka.backend.dto.RegisterRequest;
import com.renuka.backend.dto.RegisterResponse;
import com.renuka.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request){

        return userService.register(request);

    }

}
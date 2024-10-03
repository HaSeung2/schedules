package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.request.UserRequestDTO;
import com.sparta.scheduler.dto.response.UserResponseDTO;
import com.sparta.scheduler.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/checking")
    public UserResponseDTO selectUserId(@RequestParam("user_email") String user_email) {
        return userService.selectUserId(user_email);
    }


    @PostMapping("/create")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
}

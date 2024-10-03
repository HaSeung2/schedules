package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.request.UserRequestDTO;
import com.sparta.scheduler.dto.response.UserResponseDTO;
import com.sparta.scheduler.repository.UserRepository;
import com.sparta.scheduler.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO selectUserId(String user_email) {
        UserResponseDTO userResponseDTO = new UserResponseDTO(userRepository.selectUserId(user_email));
        return userResponseDTO;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO(userRepository.insert(user));

        return userResponseDTO;
    }
}

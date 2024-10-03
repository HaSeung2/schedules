package com.sparta.scheduler.entity.user;

import com.sparta.scheduler.dto.request.UserRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long user_id;
    private String user_email;
    private String username;
    private String creation_date;
    private String modification_date;

    public User(UserRequestDTO userRequestDTO) {
        this.user_email = userRequestDTO.getUser_email();
        this.username = userRequestDTO.getUsername();
    }
}

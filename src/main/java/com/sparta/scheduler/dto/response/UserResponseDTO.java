package com.sparta.scheduler.dto.response;

import com.sparta.scheduler.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long user_id;
    private String user_email;
    private String username;
    private String creation_date;
    private String modification_date;

    public UserResponseDTO(User user) {
        this.user_id = user.getUser_id();
        this.user_email = user.getUser_email();
        this.username = user.getUsername();
        this.creation_date = user.getCreation_date();
        this.modification_date = user.getModification_date();
    }
}

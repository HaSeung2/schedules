package com.sparta.scheduler.scheduler;

import com.sparta.scheduler.dto.request.SchedulerRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class Scheduler {
        private Long user_id;
        private Long schedule_id;
        private String username;
        private String password;
        private String title;
        private String content;
        private String start_date;
        private String end_date;
        private String creation_date;
        private String modification_date;

    public Scheduler(SchedulerRequestDTO schedulerRequestDTO) {
        this.user_id = schedulerRequestDTO.getUser_id();
        this.username = schedulerRequestDTO.getUsername();
        this.password = schedulerRequestDTO.getPassword();
        this.title = schedulerRequestDTO.getTitle();
        this.content = schedulerRequestDTO.getContent();
        this.start_date = schedulerRequestDTO.getStart_date();
        this.end_date = schedulerRequestDTO.getEnd_date();
    }

    public Scheduler(ResultSet resultSet) throws SQLException {
        this.user_id = resultSet.getLong("user_id");
        this.schedule_id = resultSet.getLong("schedule_id");
        this.username = resultSet.getString("username");
        this.password = resultSet.getString("password");
        this.title = resultSet.getString("title");
        this.content = resultSet.getString("content");
        this.start_date = resultSet.getString("start_date");
        this.end_date = resultSet.getString("end_date");
        this.creation_date = resultSet.getString("creation_date");
        this.modification_date = resultSet.getString("modification_date");
    }
}

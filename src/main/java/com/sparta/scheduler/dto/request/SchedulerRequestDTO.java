package com.sparta.scheduler.dto.request;

import lombok.Getter;

@Getter
public class SchedulerRequestDTO {
        private Long user_id;
        private String username;
        private String password;
        private String title;
        private String start_date;
        private String end_date;
        private String content;
}

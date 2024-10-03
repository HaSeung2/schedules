package com.sparta.scheduler.dto.response;

import com.sparta.scheduler.entity.scheduler.Scheduler;
import lombok.Getter;

@Getter
public class SchedulerResponseDTO {
        private  Long user_id;
        private  Long schedule_id;
        private  String username;
        private  String password;
        private  String title;
        private  String content;
        private  String start_date;
        private  String end_date;
        private  String creation_date;
        private  String modification_date;

    public SchedulerResponseDTO(Scheduler scheduler) {
        this.user_id = scheduler.getUser_id();
        this.schedule_id = scheduler.getSchedule_id();
        this.username = scheduler.getUsername();
        this.title = scheduler.getTitle();
        this.content = scheduler.getContent();
        this.start_date = scheduler.getStart_date();
        this.end_date = scheduler.getEnd_date();
        this.creation_date = scheduler.getCreation_date();
        this.modification_date = scheduler.getModification_date();
    }
}

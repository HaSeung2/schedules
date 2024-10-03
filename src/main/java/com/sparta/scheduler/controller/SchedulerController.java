package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.request.SchedulerRequestDTO;
import com.sparta.scheduler.dto.response.SchedulerResponseDTO;
import com.sparta.scheduler.page.Page;
import com.sparta.scheduler.service.SchedulerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchedulerController {

        private final SchedulerService schedulerService;

        public SchedulerController(SchedulerService schedulerService){
            this.schedulerService = schedulerService;
        }

        @PostMapping("/scheduler")
        public SchedulerResponseDTO createSchedule(@RequestBody SchedulerRequestDTO schedulerRequestDTO){
             return schedulerService.createSchedule(schedulerRequestDTO);
        }

        @GetMapping("/scheduler")
        public List<SchedulerResponseDTO> getAllSchedules(){
            return schedulerService.getAllSchedules();
        }

        @GetMapping("/scheduler/paging")
        public List<SchedulerResponseDTO> getSchedulesPaging(@RequestBody Page page){
            return schedulerService.getSchedulesPaging(page);
        }

        @GetMapping("/scheduler/{schedule_id}")
        public SchedulerResponseDTO getOneSchedule(@PathVariable("schedule_id") Long schedule_id){
            return schedulerService.getOneSchedule(schedule_id);
        }

        @PostMapping("/passwordCheck")
        public Long checkPassword(@RequestParam("schedule_id") Long schedule_id, @RequestParam("password") String password){
            return schedulerService.checkPassword(schedule_id, password);
        }

        @DeleteMapping("/scheduler/{schedule_id}")
        public Long deleteSchedule(@PathVariable("schedule_id") Long schedule_id){
            return schedulerService.deleteSchedule(schedule_id);
        }

        @PutMapping("/scheduler/{schedule_id}")
        public Long updateSchedule(@PathVariable("schedule_id") Long schedule_id, @RequestBody SchedulerRequestDTO schedulerRequestDTO){
            return schedulerService.updateSchedule(schedule_id,schedulerRequestDTO);
        }

        @GetMapping("/scheduler/search")
        public List<SchedulerResponseDTO> searchSchedule(@RequestParam("username") String username, @RequestParam("date") String date){
            return schedulerService.getScheduleBySearch(username,date);
        }

        @GetMapping("/scheduler/mySchedule/{user_id}")
        public List<SchedulerResponseDTO> getMySchedule(@PathVariable("user_id") Long user_id){
            return schedulerService.getMySchedule(user_id);
        }

}

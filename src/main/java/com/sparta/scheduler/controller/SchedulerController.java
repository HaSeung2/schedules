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

//        @GetMapping("/scheduler")
//        public List<SchedulerResponseDTO> getAllSchedules(){
//            return schedulerService.getAllSchedules();
//        }

        @GetMapping("/scheduler/paging")
        public List<SchedulerResponseDTO> getSchedulesPaging(@RequestParam(required = false, defaultValue = "1") int pageNum){
            return schedulerService.getSchedulesPaging(pageNum);
        }

        @GetMapping("/scheduler/page")
        public Page getPage(@RequestParam(required = false, defaultValue = "1") int pageNum, @RequestParam(required = false, defaultValue = "0") int total){
            List <SchedulerResponseDTO> schedules = schedulerService.getAllSchedules();
            if(total != 0){
                return new Page(total, pageNum,10,10,schedules);
            }
            total = schedules.size();
            return new Page(total, pageNum,10,10,schedules);
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
        public List<SchedulerResponseDTO> searchSchedule(@RequestParam("username") String username, @RequestParam("date") String date,@RequestParam(required = false, defaultValue = "1") int pageNum){
            return schedulerService.getScheduleBySearch(username,date,pageNum);
        }

        @GetMapping("/scheduler/searchByLength")
        public List<SchedulerResponseDTO> searchScheduleLength(@RequestParam("username") String username, @RequestParam("date") String date){
            return schedulerService.getScheduleBySearchLength(username,date);
        }


        @GetMapping("/scheduler/mySchedule")
        public List<SchedulerResponseDTO> getMySchedule(Long user_id, @RequestParam(required = false, defaultValue = "1")int pageNum){
            return schedulerService.getMySchedule(user_id,pageNum);
        }

        @GetMapping("/scheduler/myScheduleCnt/{user_id}")
        public int getMyScheduleCnt(@PathVariable("user_id") Long user_id){
            return schedulerService.getMyScheduleCnt(user_id);
        }
}

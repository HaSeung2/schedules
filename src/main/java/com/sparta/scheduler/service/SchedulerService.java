package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.request.SchedulerRequestDTO;
import com.sparta.scheduler.dto.response.SchedulerResponseDTO;
import com.sparta.scheduler.repository.SchedulerRepository;
import com.sparta.scheduler.entity.scheduler.Scheduler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {
        private final SchedulerRepository schedulerRepository;

        public SchedulerService(SchedulerRepository schedulerRepository) {
            this.schedulerRepository = schedulerRepository;
        }

        public SchedulerResponseDTO createSchedule(SchedulerRequestDTO schedulerRequestDTO) {
            Scheduler scheduler = new Scheduler(schedulerRequestDTO);

            scheduler = schedulerRepository.insert(scheduler);
            return new SchedulerResponseDTO(scheduler);
        }

        public SchedulerResponseDTO getOneSchedule(Long schedule_id) {
                Scheduler scheduler = schedulerRepository.findById(schedule_id);
                return new SchedulerResponseDTO(scheduler);
        }

        public Long deleteSchedule(Long schedule_id) {
                return schedulerRepository.delete(schedule_id);
        }

        public Long checkPassword(Long schedule_id, String password) {
                return schedulerRepository.checkPassword(schedule_id,password);
        }

        public Long updateSchedule(Long schedule_id, SchedulerRequestDTO schedulerRequestDTO) {
                Scheduler updatedScheduler = new Scheduler(schedulerRequestDTO);
                return schedulerRepository.update(schedule_id,updatedScheduler);
        }

        public List<SchedulerResponseDTO> getAllSchedules() {
                return schedulerRepository.selectAll().stream().map(SchedulerResponseDTO :: new).toList();
        }


        public List<SchedulerResponseDTO> getSchedulesPaging(int pageNum) {
                if(pageNum != 1) pageNum *= 10;
                return schedulerRepository.selectAllByPaging(pageNum).stream().map(SchedulerResponseDTO::new).toList();
        }

        public List<SchedulerResponseDTO> getScheduleBySearch(String username, String date,int pageNum) {
                if(pageNum != 1) pageNum *= 10;
                if(username.isEmpty() && !date.isEmpty()) {
                        return schedulerRepository.selectSearchModifyDate(date,pageNum).stream().map(SchedulerResponseDTO :: new).toList();
                }
                else if(!username.isEmpty() && date.isEmpty()) {
                        return schedulerRepository.selectSearchAuthor(username,pageNum).stream().map(SchedulerResponseDTO :: new).toList();
                }
                else{
                        return schedulerRepository.selectSearchAll(username,date,pageNum).stream().map(SchedulerResponseDTO :: new).toList();
                }
        }

        public List<SchedulerResponseDTO> getScheduleBySearchLength(String username, String date) {
                if(username.isEmpty() && !date.isEmpty()) {
                        return schedulerRepository.selectSearchModifyDateLength(date).stream().map(SchedulerResponseDTO :: new).toList();
                }
                else if(!username.isEmpty() && date.isEmpty()) {
                        return schedulerRepository.selectSearchAuthorLength(username).stream().map(SchedulerResponseDTO :: new).toList();
                }
                else{
                        return schedulerRepository.selectSearchAllLength(username,date).stream().map(SchedulerResponseDTO :: new).toList();
                }
        }

        public List<SchedulerResponseDTO> getMySchedule(Long user_id, int pageNum) {
                if(pageNum != 1) pageNum *= 10;
                return schedulerRepository.selectByMySchedule(user_id,pageNum).stream().map(SchedulerResponseDTO :: new).toList();
        }

        public int getMyScheduleCnt(Long user_id) {
                return schedulerRepository.selectByMyScheduleCnt(user_id).size();
        }
}

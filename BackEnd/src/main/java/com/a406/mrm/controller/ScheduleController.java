package com.a406.mrm.controller;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.model.dto.ScheduleResponseDto;
import com.a406.mrm.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    private final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("{userId}")
    public ResponseEntity<?> getSchedule(@PathVariable("userId") String userId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedule(userId));
    }

    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){
        logger.info("Schedule RequestDto : {} ", scheduleRequestDto.toString());
        ScheduleResponseDto scheduleResponseDto = scheduleService.addSchedule(scheduleRequestDto);
        logger.info("Schedule ResponseDto : {} ", scheduleResponseDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponseDto);
    }

    @DeleteMapping("{scheduleId}")
    public ResponseEntity<?> removeSchedule(@PathVariable("scheduleId") int scheduleId){
        scheduleService.removeSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("{scheduleId}")
    public ResponseEntity<?> modifySchedule(@PathVariable("scheduleId") int scheduleId,
                                            @RequestBody ScheduleRequestDto scheduleRequestDto){
        ScheduleResponseDto scheduleResponseDto = scheduleService.modifySchedule(scheduleId,scheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseDto);
    }
}

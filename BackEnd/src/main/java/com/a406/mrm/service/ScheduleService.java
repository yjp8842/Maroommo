package com.a406.mrm.service;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.model.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDto> getSchedule(String userId);
    ScheduleResponseDto addSchedule(ScheduleRequestDto scheduleRequestDto);
    void removeSchedule(int scheduleId);
    ScheduleResponseDto modifySchedule(int scheduleId, ScheduleRequestDto scheduleRequestDto);
}

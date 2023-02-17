package com.a406.mrm.service;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.model.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDto> getSchedule(String userId) throws Exception;
    ScheduleResponseDto addSchedule(ScheduleRequestDto scheduleRequestDto) throws Exception;
    void removeSchedule(int scheduleId) throws Exception;
    ScheduleResponseDto modifySchedule(int scheduleId, ScheduleRequestDto scheduleRequestDto) throws Exception;
}

package com.a406.mrm.service;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.model.dto.ScheduleResponseDto;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.Schedule;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.RoomRepository;
import com.a406.mrm.repository.ScheduleRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    private final ScheduleRepository scheduleRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public List<ScheduleResponseDto> getSchedule(String userId) {
        List<ScheduleResponseDto> result = scheduleRepository.findByUserId(userId)
                .stream()
                .map(x -> new ScheduleResponseDto(x))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto scheduleRequestDto) {
        Optional<Room> room = roomRepository.findById(scheduleRequestDto.getRoomId());
        User user = userRepository.findById(scheduleRequestDto.getUserId()).get();
        Schedule schedule = scheduleRepository.save(new Schedule(scheduleRequestDto,user,room.get()));
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void removeSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    public ScheduleResponseDto updateSchedule(int scheduleId, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        schedule.setStartTime(scheduleRequestDto.getStartTime());
        schedule.setEndTime(scheduleRequestDto.getEndTime());
        schedule.setContent(scheduleRequestDto.getContent());
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }
}

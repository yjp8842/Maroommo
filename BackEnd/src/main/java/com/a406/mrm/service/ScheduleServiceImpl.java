package com.a406.mrm.service;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.model.dto.ScheduleResponseDto;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.Schedule;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.model.entity.UserHasRoom;
import com.a406.mrm.repository.RoomRepository;
import com.a406.mrm.repository.ScheduleRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    // 유저가 속한 그룹들을 불러옴
    // 그룹들마다 스케쥴을 불러와 저장한다
    @Override
    public List<ScheduleResponseDto> getSchedule(String userId) {
        List<ScheduleResponseDto> result = new ArrayList<>();

        List<Integer> rooms = userRepository.findById(userId)
                                            .get()
                                            .getRooms()
                                            .stream()
                                            .map(x->x.getRoom().getId())
                                            .collect(Collectors.toList());

        for (int roomId : rooms){
            List<ScheduleResponseDto> list = scheduleRepository.findByRoomId(roomId)
                    .stream()
                    .map(x->new ScheduleResponseDto(x))
                    .collect(Collectors.toList());
            for(ScheduleResponseDto s : list){
                result.add(s);
            }
        }

        return result;
    }

    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto scheduleRequestDto) {
        Room room = roomRepository.findById(scheduleRequestDto.getRoomId()).get();
        User user = userRepository.findById(scheduleRequestDto.getUserId()).get();
        ScheduleResponseDto scheduleResponseDto = null;

        if(room != null && user != null){
            Schedule schedule = new Schedule(scheduleRequestDto,user,room);
            schedule = scheduleRepository.save(schedule);
            scheduleResponseDto = new ScheduleResponseDto(schedule);
        }

        return scheduleResponseDto;
    }

    @Override
    public void removeSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    public ScheduleResponseDto modifySchedule(int scheduleId, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        ScheduleResponseDto scheduleResponseDto = null;

        if (schedule != null){
            schedule.setStartTime(scheduleRequestDto.getStartTime());
            schedule.setContent(scheduleRequestDto.getContent());
            schedule = scheduleRepository.save(schedule);
            scheduleResponseDto = new ScheduleResponseDto(schedule);
        }

        return scheduleResponseDto;
    }
}

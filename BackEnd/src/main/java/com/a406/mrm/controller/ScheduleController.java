package com.a406.mrm.controller;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.model.dto.ScheduleResponseDto;
import com.a406.mrm.repository.ScheduleRepository;
import com.a406.mrm.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;
    
    // 유저의 스케쥴을 가져온다...?
    // 지금 논의되는 스케쥴은 그룹의 스케쥴이기도 하고
    // room 이동 시 스케쥴을 가져오기 때문에
    // 여기서 get을 할 순간이 있는가... 를 생각해보자
    /**
     * @param userId
     *            를 통해 회원이 속한 모든 그룹들의 스케쥴들을 가져온다
     * @return schedules : 회원이 속한 모든 그룹들의 스케쥴들을 반환한다
     */
    @GetMapping("{userId}")
    public ResponseEntity<?> getSchedule(@PathVariable("userId") String userId){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        List<ScheduleResponseDto> scheduleResponseDtoList = null;

        try {
            scheduleResponseDtoList = scheduleService.getSchedule(userId);
            resultMap.put("schedules",scheduleResponseDtoList);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param scheduleRequestDto
     *                      를 통해 스케쥴을 생성한다
     * @return newSchedule : 생성한 스케쥴을 반환한다
     */
    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){
        logger.info("Schedule RequestDto : {} ", scheduleRequestDto.toString());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        ScheduleResponseDto scheduleResponseDto = null;

        try {
            scheduleResponseDto = scheduleService.addSchedule(scheduleRequestDto);
            resultMap.put("newSchedule",scheduleResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param scheduleId
     *              를 통해 스케쥴을 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @DeleteMapping("{scheduleId}")
    public ResponseEntity<?> removeSchedule(@PathVariable("scheduleId") int scheduleId){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            scheduleService.removeSchedule(scheduleId);
            resultMap.put("isDelete",true);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param scheduleId
     * @param scheduleRequestDto
     *                      를 통해 스케쥴을 수정한다
     * @return schedule : 수정한 스케쥴을 반환한다
     */
    @PatchMapping("{scheduleId}")
    public ResponseEntity<?> modifySchedule(@PathVariable("scheduleId") int scheduleId,
                                            @RequestBody ScheduleRequestDto scheduleRequestDto){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        ScheduleResponseDto scheduleResponseDto = null;

        try {
            scheduleResponseDto = scheduleService.modifySchedule(scheduleId,scheduleRequestDto);
            resultMap.put("schedule",scheduleResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

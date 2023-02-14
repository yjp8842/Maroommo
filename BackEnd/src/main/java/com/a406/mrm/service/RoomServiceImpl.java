package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.*;
import com.a406.mrm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;
import java.util.stream.Collectors;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserHasRoomRepository userHasRoomRepository;
    private final ScheduleService scheduleService;
    private final RoomMemoRepository roomMemoRepository;
    private final TodoTimeService todoTimeService;

    // 마이 페이지를 눌렀을 시 유저가 참가한 room list를 가져온다
    @Override
    public MyRoomResponseDto getMyRoomDto(String userId) throws Exception{
        MyRoomResponseDto myRoomResponseDto = null;
        User user = userRepository.findById(userId).get();
        List<ScheduleResponseDto> schedules = scheduleService.getSchedule(userId);

        // 애초에 user가 없는데 요청을 할 일이 없긴 하지,,,
        if(user != null){
            myRoomResponseDto = new MyRoomResponseDto(user,schedules);
        }
        return myRoomResponseDto;
    }

    // 그룹 페이지를 눌렀을 시 그룹의 자세한 정보를 받아온다
    @Override
    public RoomMoveResponseDto getMoveRoomDto(int roomId, String userId) throws Exception{
        RoomMoveResponseDto moveRoomResponseDto = null;
        Room room = roomRepository.findById(roomId).get();
        List<ScheduleResponseDto> schedules = scheduleService.getSchedule(userId);
        List<TodoTimeDto> todoTimes = todoTimeService.getTodayTodoTime(roomId);
        RoomMemo roomMemo = roomMemoRepository.findByRoomId(roomId);
        if(room != null){
            moveRoomResponseDto = new RoomMoveResponseDto(room, roomMemo,schedules,todoTimes);
        }

        return moveRoomResponseDto;
    }

    // room을 생성하고 해당 room으로 이돟한다 -> detail한 정보를 반환해야 하고 room list도 줘야한다
    @Override
    public RoomMoveResponseDto makeRoom(RoomRequestDto roomRequestDto, String userId, MultipartFile profile) throws Exception{
        RoomMoveResponseDto moveRoomResponseDto = null;

        // 파일 저장
        String uuid =  null;
        if(profile != null){
            uuid = UUID.randomUUID().toString()+"."+profile.getOriginalFilename().substring(profile.getOriginalFilename().lastIndexOf(".")+1);
//            String absPath = "C:/SSAFY/S08P12A406/img_dir/"+uuid;
            String absPath = "/img_dir/"+uuid;
            try {
                profile.transferTo(new File(absPath));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Room registRoom = new Room(roomRequestDto,uuid); // 입력받은 room 정보를 세팅한 후

        String code = UUID.randomUUID().toString(); // entry code를 생성하여
        registRoom.setCode(code); // 추가
        registRoom = roomRepository.save(registRoom);

        // 유저에 룸 연동
        User user = userRepository.findById(userId).get();
        if(user == null) return null;
        UserHasRoom userHasRoom = new UserHasRoom(user, registRoom);
        userHasRoomRepository.save(userHasRoom);

        // 그룹 메모를 저장
        RoomMemo roomMemo = roomMemoRepository.save(new RoomMemo(registRoom.getId(), ""));

        List<ScheduleResponseDto> schedules = scheduleService.getSchedule(userId);
        moveRoomResponseDto = new RoomMoveResponseDto(registRoom, roomMemo, schedules);

        return moveRoomResponseDto;
    }

    // room 입장
    @Override
    public RoomMoveResponseDto enterRoom(int roomId, String userId) throws Exception{
        RoomMoveResponseDto moveRoomResponseDto = null;
        Room enterRoomInfo = roomRepository.findById(roomId).get(); // id가 일치하는 room 정보를 가져온다
        RoomMemo roomMemo = roomMemoRepository.findByRoomId(roomId);
        User user = userRepository.findById(userId).get();
        List<ScheduleResponseDto> schedules = scheduleService.getSchedule(userId);
        UserHasRoom userHasRoom = null;

        if(user != null && enterRoomInfo != null){
            userHasRoom = new UserHasRoom(user, enterRoomInfo);
            userHasRoomRepository.save(userHasRoom);
            moveRoomResponseDto = new RoomMoveResponseDto(enterRoomInfo, roomMemo, schedules);
        }

        return moveRoomResponseDto;
    }

    @Override
    public boolean existsRoomByIdAndCode(int roomId, String code) throws Exception{
        return roomRepository.existsByIdAndCode(roomId, code);
    }

    @Override
    public boolean existsUserHasRoomByRoomIdAndUserId(int roomId, String userId) throws Exception{
        return userHasRoomRepository.existsByRoomIdAndUserId(roomId, userId);
    }

    // code 갱신
    @Override
    public String updateCode(int roomId) throws Exception{
        Room nowRoomInfo = roomRepository.findById(roomId).get(); // room 정보를 가져온다
        String newCode = null;

        if (nowRoomInfo != null){
            newCode = UUID.randomUUID().toString();
            nowRoomInfo.setCode(newCode);
            roomRepository.save(nowRoomInfo);
        }

        return newCode;
    }

    @Override
    public void removeRoom(int roomId)throws Exception{
        Room room = roomRepository.findById(roomId).get();

        if(room.getUsers() != null) {
            room.getUsers().removeIf(r->r.getRoom().getId() == roomId);
            userHasRoomRepository.saveAll(room.getUsers());
        }

        roomRepository.delete(room);
    }

    @Override
    public String modifyName(int roomId, String name)throws Exception{
        Room room = roomRepository.findById(roomId).get();
        String roomName = null;

        if (room != null){
            room.setName(name);
            room = roomRepository.save(room);
            roomName = room.getName();
        }

        return roomName;
    }

    @Override
    public String modifyIntro(int roomId, String intro)throws Exception{
        Room room = roomRepository.findById(roomId).get();
        String res = null;

        if(room != null){
            room.setIntro(intro);
            room = roomRepository.save(room);
            res = room.getIntro();
        }

        return res;
    }

    @Override
    public String modifyProfile(int roomId, MultipartFile profile) throws Exception{
        Room room = roomRepository.findById(roomId).get();
        String res =  null;

        if(room != null && profile != null){
            String uuid = UUID.randomUUID().toString()+"."+profile.getOriginalFilename().substring(profile.getOriginalFilename().lastIndexOf(".")+1);
//            String absPath = "C:/SSAFY/S08P12A406/img_dir/"+uuid;
            String absPath = "/img_dir/"+uuid;
            try {
                profile.transferTo(new File(absPath));
                room.setProfile(uuid);
                room = roomRepository.save(room);
                res = room.getProfile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return res;
    }


    public List<RoomMoveResponseDto> RoomListAll () throws Exception{
        List<RoomMoveResponseDto> result =
                roomRepository.RoomListAll()
                                .stream()
                                .map(x -> new RoomMoveResponseDto(x, roomMemoRepository.findByRoomId(x.getId()), null)).collect(Collectors.toList());
        return result;
    }

}

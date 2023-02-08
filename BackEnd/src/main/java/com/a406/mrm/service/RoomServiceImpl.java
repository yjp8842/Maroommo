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
    private final RoomMemoRepository roomMemoRepository;

    // 마이 페이지를 눌렀을 시 유저가 참가한 room list를 가져온다
    @Override
    public MyRoomResponseDto getMyRoomDto(String userId) {
        MyRoomResponseDto myRoomResponseDto = null;
        User user = userRepository.findById(userId).get();

        // 애초에 user가 없는데 요청을 할 일이 없긴 하지,,,
        if(user != null){
            myRoomResponseDto = new MyRoomResponseDto(user);
        }
        return myRoomResponseDto;
    }

    // 그룹 페이지를 눌렀을 시 그룹의 자세한 정보를 받아온다
    @Override
    public RoomMoveResponseDto getMoveRoomDto(int roomId) {
        RoomMoveResponseDto moveRoomResponseDto = null;
        Room room = roomRepository.findById(roomId).get();
        RoomMemo roomMemo = roomMemoRepository.findByRoomId(roomId);

        if(room != null){
            moveRoomResponseDto = new RoomMoveResponseDto(room, roomMemo);
        }

        return moveRoomResponseDto;
    }

    // room을 생성하고 해당 room으로 이돟한다 -> detail한 정보를 반환해야 하고 room list도 줘야한다
    @Override
    public RoomMoveResponseDto makeRoom(RoomRequestDto roomRequestDto, String userId, MultipartFile profile) {
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
        UserHasRoom userHasRoom = new UserHasRoom(user, registRoom);
        userHasRoomRepository.save(userHasRoom);

        // 그룹 메모를 저장
        RoomMemo roomMemo = roomMemoRepository.save(new RoomMemo(registRoom.getId(), ""));

        moveRoomResponseDto = new RoomMoveResponseDto(registRoom, roomMemo);

        return moveRoomResponseDto;
    }

    // room 입장
    @Override
    public RoomMoveResponseDto enterRoom(int roomId, String userId) {
        RoomMoveResponseDto moveRoomResponseDto = null;
        Room enterRoomInfo = roomRepository.findById(roomId).get(); // id가 일치하는 room 정보를 가져온다
        RoomMemo roomMemo = roomMemoRepository.findByRoomId(roomId);
        User user = userRepository.findById(userId).get();
        UserHasRoom userHasRoom = null;

        if(enterRoomInfo != null){
            userHasRoom = new UserHasRoom(user, enterRoomInfo);
            userHasRoomRepository.save(userHasRoom);
            moveRoomResponseDto = new RoomMoveResponseDto(enterRoomInfo, roomMemo);
        }

        return moveRoomResponseDto;
    }

    @Override
    public boolean existsRoomByIdAndCode(int roomId, String code) {
        return roomRepository.existsByIdAndCode(roomId, code);
    }

    @Override
    public boolean existsUserHasRoomByRoomIdAndUserId(int roomId, String userId) {
        return userHasRoomRepository.existsByRoomIdAndUserId(roomId, userId);
    }

    // code 갱신
    @Override
    public String updateCode(int roomId) {
        Room nowRoomInfo = roomRepository.findById(roomId).get(); // room 정보를 가져온다
        String newCode = UUID.randomUUID().toString();
        nowRoomInfo.setCode(newCode);
        roomRepository.save(nowRoomInfo);

        return newCode;
    }

    @Override
    public void removeRoom(int roomId){
        Room room = roomRepository.findById(roomId).get();
        if(room.getUsers() != null) {
//            room.getUsers().stream().map(r-> r.getUser().getRooms().removeIf(u -> u.getRoom().getId() == roomId));
            room.getUsers().removeIf(r->r.getRoom().getId() == roomId);
            userHasRoomRepository.saveAll(room.getUsers());
        }
        roomRepository.delete(room);
    }

    @Override
    public String modifyName(int roomId, String name){
        Room room = roomRepository.findById(roomId).get();
        room.setName(name);
        return roomRepository.save(room).getName();
    }

    @Override
    public String modifyIntro(int roomId, String intro){
        Room room = roomRepository.findById(roomId).get();
        room.setIntro(intro);
        return roomRepository.save(room).getIntro();
    }

    @Override
    public String modifyProfile(int roomId, MultipartFile profile) {
        Room room = roomRepository.findById(roomId).get();
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
        room.setProfile(uuid);
        return roomRepository.save(room).getProfile();
    }


    public List<RoomMoveResponseDto> RoomListAll () {
        List<RoomMoveResponseDto> result =
                roomRepository.RoomListAll()
                                .stream()
                                .map(x -> new RoomMoveResponseDto(x, roomMemoRepository.findByRoomId(x.getId()))).collect(Collectors.toList());
        return result;
    }

}

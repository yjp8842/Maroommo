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
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserHasRoomRepository userHasRoomRepository;
    private final RoomMemoRepository roomMemoRepository;
    private final TodoRepository todoRepository;
    private final TodoTimeRepository todoTimeRepository;

    @Override
    public RoomResponseDto makeRoom(RoomRequestDto roomRequestDto, String userId, MultipartFile profile) {
        // room의 users에 추가
        // user의 rooms에 추가
        // ManyToMany 공부 필요 ;-;

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
        Room roomInfo = new Room(roomRequestDto,uuid); // 입력받은 room 정보를 세팅한 후
        String code = createEntryCode(); // entry code를 생성하여
//        String code = UUID.randomUUID().toString();
        roomInfo.setCode(code); // 추가
        Room roomRegisterResult = roomRepository.save(roomInfo);

        // 유저에 룸 연동
        User user = userRepository.findById(userId).get();
        UserHasRoom userHasRoom = new UserHasRoom();
        userHasRoom.setRoom(roomRegisterResult);
        userHasRoom.setUser(user);
        userHasRoomRepository.save(userHasRoom);

        // 그룹 메모를 저장
        RoomMemo roomMemo = roomMemoRepository.save(new RoomMemo(roomRegisterResult.getId(), ""));
        RoomResponseDto roomResponseDto = new RoomResponseDto(roomRegisterResult, roomMemo);

        return roomResponseDto;
    }

    // room 입장
    @Override
    public RoomResponseDto enterRoom(int roomId, String userId) {
        Room enterRoomInfo = roomRepository.findById(roomId).get(); // id가 일치하는 room 정보를 가져온다

        // room의 users에 추가
        // user의 rooms에 추가
        User user = userRepository.findById(userId).get();
        UserHasRoom userHasRoom = new UserHasRoom();
        userHasRoom.setRoom(enterRoomInfo);
        userHasRoom.setUser(user);
        userHasRoomRepository.save(userHasRoom);

        RoomMemo roomMemo = roomMemoRepository.findByRoomId(roomId);

        RoomResponseDto roomResponseDto = new RoomResponseDto(enterRoomInfo, roomMemo);

        return roomResponseDto;
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
        String newCode = createEntryCode();
//        String newCode = UUID.randomUUID().toString();
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


    public static String createEntryCode() {
        StringBuffer code = new StringBuffer();
        Random rnd = new Random();
        int codeLength = 32;

        for (int i = 0; i < codeLength; i++) { // 입장 코드 16자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    code.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z
                    break;
                case 1:
                    code.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    code.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return code.toString();
    }


    public List<RoomMoveResponseDto> RoomListAll () {
        List<RoomMoveResponseDto> result = roomRepository.RoomListAll()
                .stream()
                .map(x -> new RoomMoveResponseDto(x)).collect(Collectors.toList());
        return result;
    }

}

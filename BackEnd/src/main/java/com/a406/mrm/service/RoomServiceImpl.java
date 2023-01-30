package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomRequestDto;
import com.a406.mrm.model.dto.RoomResponseDto;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.model.entity.UserHasRoom;
import com.a406.mrm.repository.RoomRepository;
import com.a406.mrm.repository.UserHasRoomRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserHasRoomRepository userHasRoomRepository;
    @Override
    public Room makeRoom(RoomRequestDto roomRequestDto, String userId) {
        // room의 users에 추가
        // user의 rooms에 추가
        // ManyToMany 공부 필요 ;-;
        Room roomRegisterResult = roomRepository.save(new Room(roomRequestDto));
        User user = userRepository.findById(userId).get();
        UserHasRoom userHasRoom = new UserHasRoom();
        userHasRoom.setRoom(roomRegisterResult);
        userHasRoom.setUser(user);
        userHasRoomRepository.save(userHasRoom);
        return roomRegisterResult;
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
    public String modifyProfile(int roomId, String profile){
        Room room = roomRepository.findById(roomId).get();
        room.setProfile(profile);
        return roomRepository.save(room).getProfile();
    }

    @Override
    public String modifyMemo(int roomId, String memo) {
        Room room = roomRepository.findById(roomId).get();
        room.setMemo(memo);
        return roomRepository.save(room).getMemo();
    }
}

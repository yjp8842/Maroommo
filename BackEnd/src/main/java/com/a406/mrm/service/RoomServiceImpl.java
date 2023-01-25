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
    @Transactional
    public String modifyMemo(int roomId, String memo) {
        Room room = roomRepository.findById(roomId).get();
        room.setMemo(memo);
        return roomRepository.save(room).getMemo();
    }
}

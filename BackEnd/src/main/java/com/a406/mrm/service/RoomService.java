package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomRegisterFormDto;
import com.a406.mrm.model.entity.Room;
import org.springframework.http.ResponseEntity;

public interface RoomService {
    Room makeRoom(Room room, String userId);
    String modifyMemo(int roomId, String memo);
}

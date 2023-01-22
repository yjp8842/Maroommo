package com.a406.mrm.service;

import com.a406.mrm.model.entity.Room;

public interface RoomService {
    Room makeRoom(Room room, String userId);
    String modifyMemo(int roomId, String memo);
}

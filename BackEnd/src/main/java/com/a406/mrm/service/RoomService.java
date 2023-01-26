package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomDto;
import com.a406.mrm.model.entity.Room;

public interface RoomService {
    Room makeRoom(RoomDto roomDto, String userId);
    void removeRoom(int roomId);
    String modifyName(int roomId, String name);
    String modifyIntro(int roomId, String intro);
    String modifyProfile(int roomId, String profile);
    String modifyMemo(int roomId, String memo);
}

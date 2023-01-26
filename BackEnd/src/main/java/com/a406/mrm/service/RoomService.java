package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomRequestDto;
import com.a406.mrm.model.dto.RoomResponseDto;
import com.a406.mrm.model.entity.Room;

public interface RoomService {
    void removeRoom(int roomId);
    String modifyName(int roomId, String name);
    String modifyIntro(int roomId, String intro);
    String modifyProfile(int roomId, String profile);
    Room makeRoom(RoomRequestDto roomRequestDto, String userId);
    String modifyMemo(int roomId, String memo);
}

package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomAllResponseDto;
import com.a406.mrm.model.dto.RoomRequestDto;
import com.a406.mrm.model.entity.Room;

import java.util.List;

public interface RoomService {
    void removeRoom(int roomId);
    String modifyName(int roomId, String name);
    String modifyIntro(int roomId, String intro);
    String modifyProfile(int roomId, String profile);
    Room makeRoom(RoomRequestDto roomRequestDto, String userId);
    Room enterRoom(int roomId, String userId);
    boolean existsRoomByIdAndCode(int roomId, String code);
    boolean existsUserHasRoomByRoomIdAndUserId(int roomId, String userId);
    String updateCode(int roomId);
    String modifyMemo(int roomId, String memo);
    List<RoomAllResponseDto> SearchRoom(int room_id);

    List<RoomAllResponseDto> RoomListAll();

}

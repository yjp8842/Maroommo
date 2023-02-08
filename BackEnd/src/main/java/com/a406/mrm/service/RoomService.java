package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomMoveResponseDto;
import com.a406.mrm.model.dto.RoomRequestDto;
import com.a406.mrm.model.dto.RoomResponseDto;
import com.a406.mrm.model.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    void removeRoom(int roomId);
    String modifyName(int roomId, String name);
    String modifyIntro(int roomId, String intro);
    String modifyProfile(int roomId, MultipartFile profile);
    RoomResponseDto makeRoom(RoomRequestDto roomRequestDto, String userId, MultipartFile profile);
    RoomResponseDto enterRoom(int roomId, String userId);
    boolean existsRoomByIdAndCode(int roomId, String code);
    boolean existsUserHasRoomByRoomIdAndUserId(int roomId, String userId);
    String updateCode(int roomId);

//    List<RoomAllResponseDto> SearchRoom(int room_id);

    List<RoomMoveResponseDto> RoomListAll();

}

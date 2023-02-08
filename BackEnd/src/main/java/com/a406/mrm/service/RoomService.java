package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.UserHasRoom;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    MyRoomResponseDto getMyRoomDto(String userId);
    RoomMoveResponseDto getMoveRoomDto(int roomId);
    void removeRoom(int roomId);
    String modifyName(int roomId, String name);
    String modifyIntro(int roomId, String intro);
    String modifyProfile(int roomId, MultipartFile profile);
    RoomMoveResponseDto makeRoom(RoomRequestDto roomRequestDto, String userId, MultipartFile profile);
    RoomMoveResponseDto enterRoom(int roomId, String userId);
    boolean existsRoomByIdAndCode(int roomId, String code);
    boolean existsUserHasRoomByRoomIdAndUserId(int roomId, String userId);
    String updateCode(int roomId);

//    List<RoomAllResponseDto> SearchRoom(int room_id);

    List<RoomMoveResponseDto> RoomListAll();

}

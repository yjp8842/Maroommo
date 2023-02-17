package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.UserHasRoom;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    MyRoomResponseDto getMyRoomDto(String userId) throws Exception;
    RoomMoveResponseDto getMoveRoomDto(int roomId, String userId) throws Exception;
    void removeRoom(int roomId) throws Exception;
    RoomMoveResponseDto makeRoom(RoomRequestDto roomRequestDto, String userId) throws Exception;
    RoomMoveResponseDto enterRoom(int roomId, String userId) throws Exception;
    boolean existsRoomByIdAndCode(int roomId, String code) throws Exception;
    boolean existsUserHasRoomByRoomIdAndUserId(int roomId, String userId) throws Exception;
    String updateCode(int roomId) throws Exception;
    List<RoomMoveResponseDto> RoomListAll() throws Exception;
    RoomResponseDto modifyInfo(int roomId, RoomDto roomDto);
}

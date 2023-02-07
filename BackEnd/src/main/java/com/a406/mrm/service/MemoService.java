package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomMemoDto;
import com.a406.mrm.model.dto.UserMemoDto;

import java.util.List;

public interface MemoService {
    void insertMemo(UserMemoDto userMemoDto) throws Exception;
    void insertMemo(RoomMemoDto roomMemoDto) throws Exception;

    UserMemoDto findUserMemoByUserId(String userId) throws Exception;
    RoomMemoDto findRoomMemoByRoomId(int roomId) throws Exception;
    List<UserMemoDto> findAllUserMemo() throws Exception;
    List<RoomMemoDto> findAllRoomMemo() throws Exception;
}

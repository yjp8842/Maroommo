package com.a406.mrm.service;


import com.a406.mrm.model.dto.MemoDto;

import java.util.List;

public interface MemoService {
    void insertMemo(MemoDto memoRequestDto) throws Exception;
    MemoDto findMemoByUserId(String userId) throws Exception;
    MemoDto findMemoByRoomId(int roomId) throws Exception;
    List<MemoDto> findAll() throws Exception;
}

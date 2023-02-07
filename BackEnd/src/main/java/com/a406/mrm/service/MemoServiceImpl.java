package com.a406.mrm.service;

import com.a406.mrm.model.dto.RoomMemoDto;
import com.a406.mrm.model.dto.UserMemoDto;
import com.a406.mrm.model.entity.RoomMemo;
import com.a406.mrm.model.entity.UserMemo;
import com.a406.mrm.repository.RoomMemoRepository;
import com.a406.mrm.repository.UserMemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService{

    private final UserMemoRepository userMemoRepository;
    private final RoomMemoRepository roomMemoRepository;

    @Override
    public void insertMemo(UserMemoDto userMemoDto) throws Exception {
        UserMemo userMemo = userMemoRepository.findByUserId(userMemoDto.getUserId());
        if(userMemo != null){
            userMemo.setContent(userMemoDto.getContent());
            userMemoRepository.save(userMemo);
        }
        else{
            userMemoRepository.save(new UserMemo(userMemoDto));
        }
    }

    @Override
    public void insertMemo(RoomMemoDto roomMemoDto) throws Exception {
        RoomMemo roomMemo = roomMemoRepository.findByRoomId(roomMemoDto.getRoomId());
        if(roomMemo != null){
            roomMemo.setContent(roomMemoDto.getContent());
            roomMemoRepository.save(roomMemo);
        }
        else{
            roomMemoRepository.save(new RoomMemo(roomMemoDto));
        }
    }

    @Override
    public UserMemoDto findUserMemoByUserId(String userId) throws Exception {
        return new UserMemoDto(userMemoRepository.findByUserId(userId));
    }

    @Override
    public RoomMemoDto findRoomMemoByRoomId(int roomId) throws Exception {
        return new RoomMemoDto(roomMemoRepository.findByRoomId(roomId));
    }

    @Override
    public List<UserMemoDto> findAllUserMemo() throws Exception {
        return userMemoRepository.findAll().stream().map(x->new UserMemoDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<RoomMemoDto> findAllRoomMemo() throws Exception {
        return roomMemoRepository.findAll().stream().map(x->new RoomMemoDto(x)).collect(Collectors.toList());
    }
}

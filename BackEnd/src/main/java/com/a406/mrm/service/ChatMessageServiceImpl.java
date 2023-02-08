package com.a406.mrm.service;

import com.a406.mrm.model.dto.ChatMessageRequestDto;
import com.a406.mrm.model.dto.ChatMessageResponseDto;
import com.a406.mrm.model.entity.ChatMessage;
import com.a406.mrm.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    private final ChatMessageRepository chatMessageRepository;

    // 해당 room의 채팅 내역을 불러온다
    public List<ChatMessageResponseDto> findAllChat(int roomId){
        List<ChatMessageResponseDto> res = new ArrayList<>();
        chatMessageRepository.findAllByRoomId(roomId).forEach(chat -> {
            res.add(new ChatMessageResponseDto(chat));
        });
        return res;
    }

    // 채팅을 db에 저장한다
    public ChatMessageResponseDto insertChat(ChatMessageRequestDto chatMessageDto){
        ChatMessage chatMessage = new ChatMessage(chatMessageDto);
        chatMessageRepository.save(chatMessage);
        return new ChatMessageResponseDto(chatMessage);
    }
}


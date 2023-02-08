package com.a406.mrm.service;

import com.a406.mrm.model.dto.ChatMessageRequestDto;
import com.a406.mrm.model.dto.ChatMessageResponseDto;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessageResponseDto> findAllChat(int roomId) throws Exception;
    ChatMessageResponseDto insertChat(ChatMessageRequestDto chatMessageDto) throws Exception;
}

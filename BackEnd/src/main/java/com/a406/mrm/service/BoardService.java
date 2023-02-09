package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    BoardInsertDto join(String title, String content, String user_id, int categorySub_id, MultipartFile image);

    String delete(int id, String user_id);

    BoardModifyDto update(BoardModifyDto boardModifyDto);

//    List<BoardResponseDto> listBoard(int categorySub_id);

    Page<BoardResponseDto> listBoard_Pageable(int categorysub_id, Pageable pageable);

//    BoardResponseDto detail(int board_id);

    List<BoardResponseCommentDto> BoardDetail(int board_id);

}
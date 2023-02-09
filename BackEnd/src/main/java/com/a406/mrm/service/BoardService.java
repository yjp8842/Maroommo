package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {


    BoardResponseCommentDto join(String title, String content, String user_id, int categorySub_id, MultipartFile picture) throws Exception;
    boolean delete(int id, String user_id) throws Exception;
    BoardResponseCommentDto update(BoardModifyDto boardModifyDto) throws Exception;
    Page<BoardResponseDto> listBoard_Pageable(int categorysub_id, Pageable pageable);
    BoardResponseCommentDto BoardDetail(int board_id) throws Exception;

}
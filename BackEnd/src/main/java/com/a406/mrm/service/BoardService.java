package com.a406.mrm.service;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.dto.BoardModifyDto;
import com.a406.mrm.model.dto.BoardResponseDto;
import com.a406.mrm.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    BoardInsertDto join(BoardInsertDto boardInsertDto, int categorysub_id, String user_id);

    void delete(int id);

    BoardModifyDto update(BoardModifyDto boardModifyDto, int board_id);

    List<BoardResponseDto> listBoard(int categorySub_id);

    Page<Board> listBoard_Pageable(Pageable pageable);



}
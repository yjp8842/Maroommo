package com.a406.mrm.service;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Board;

import java.util.List;

public interface BoardService {

    BoardInsertDto join(BoardInsertDto boardInsertDto, int categorysub_id, String user_id);

    void delete(int id);

    String update(int id, String title, String content);


}
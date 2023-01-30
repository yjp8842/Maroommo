package com.a406.mrm.controller;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.dto.BoardModifyDto;
import com.a406.mrm.model.dto.BoardResponseDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.service.BoardServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("board/")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    public BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> create(@RequestBody BoardInsertDto insertDto, @RequestParam("user_id") String user_id, @RequestParam("categorysub_id") int categorysub_id) {
        BoardInsertDto boardInsertDto = new BoardInsertDto();
        boardInsertDto.setTitle(insertDto.getTitle());
        boardInsertDto.setContent(insertDto.getContent());
        boardInsertDto.setCreatetime(insertDto.getCreatetime());
        boardInsertDto.setHit(insertDto.getHit());
        boardInsertDto.setPicture(insertDto.getPicture());
        boardInsertDto.setUser_id(user_id);
        boardInsertDto.setCategorysub_id(categorysub_id);
        return ResponseEntity.ok(boardServiceImpl.join(boardInsertDto, categorysub_id, user_id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int bid) {
        boardServiceImpl.delete(bid);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody BoardModifyDto modifyDto, @RequestParam("board_id") int board_id) {
        BoardModifyDto boardModifyDto = new BoardModifyDto();
        boardModifyDto.setTitle(modifyDto.getTitle());
        boardModifyDto.setContent(modifyDto.getContent());
        boardModifyDto.setPicture(modifyDto.getPicture());
        return ResponseEntity.ok(boardServiceImpl.update(boardModifyDto, board_id));
    }

//    @GetMapping("list")
//    public List<Board> list () {
//        return boardServiceImpl.list();
//    }

    @GetMapping("list")
    public ResponseEntity<?> BoardList (@RequestParam("categorySub_id") int categorySub_id) {
        List<BoardResponseDto> result = boardServiceImpl.listBoard(categorySub_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호
    @GetMapping("list_pageable")
    public Page<Board> BoardPageable (Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return boardServiceImpl.listBoard_Pageable(pageable);
    }

    @GetMapping("list_pageable2")
    public List<BoardResponseDto> BoardPageable2(@RequestParam("categorySub_id") int categorySub_id , Pageable pageable) {
        return null;
    }


}
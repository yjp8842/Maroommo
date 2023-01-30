package com.a406.mrm.controller;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.service.BoardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.BoardServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    public BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BoardInsertDto insertDto) {
        return ResponseEntity.ok(boardServiceImpl.join(insertDto, insertDto.getCategorysub_id(), insertDto.getUser_id()));
    }

    @DeleteMapping("{id}/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("id") int bid,@PathVariable("user_id") String user_id) {
        String ans = boardServiceImpl.delete(bid,user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody BoardModifyDto modifyDto, @RequestParam("board_id") int board_id, @RequestParam("user_id") String user_id) {
        return ResponseEntity.ok(boardServiceImpl.update(modifyDto, board_id, user_id));
    }

    //size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호
    static final int page_num = 5;
    @GetMapping
    public Page<BoardResponseDto> BoardPageable (Model model, @RequestParam("categorySub_id") int categorySub_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return boardServiceImpl.listBoard_Pageable(categorySub_id, pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detail (@PathVariable("id") int bid) {
        return ResponseEntity.status(HttpStatus.OK).body(boardServiceImpl.listBoard(bid));
    }


}
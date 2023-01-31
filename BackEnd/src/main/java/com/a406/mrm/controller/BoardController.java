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

    @DeleteMapping("delete/{id}/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("id") int bid,@PathVariable("user_id") String user_id) {
        String ans = boardServiceImpl.delete(bid,user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody BoardModifyDto modifyDto, @RequestParam("board_id") int board_id) {
        return ResponseEntity.ok(boardServiceImpl.update(modifyDto, board_id));
    }

//    @GetMapping("list")
//    public ResponseEntity<?> BoardList (@RequestParam("categorySub_id") int categorySub_id) {
//        List<BoardResponseDto> result = boardServiceImpl.listBoard(categorySub_id);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    //size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호
    static final int page_num = 3;
    @GetMapping("list")
    public Page<BoardResponseDto> BoardPageable (Model model, @RequestParam("categorySub_id") int categorySub_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return boardServiceImpl.listBoard_Pageable(categorySub_id, pageable);
    }

//    @GetMapping("detail/{id}")
//    public ResponseEntity<?> detail (@PathVariable("id") int bid) {
//        BoardResponseDto result = boardServiceImpl.detail(bid);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail (@PathVariable("id") int bid) {
        List<BoardResponseCommentDto> result = boardServiceImpl.listBoard(bid);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
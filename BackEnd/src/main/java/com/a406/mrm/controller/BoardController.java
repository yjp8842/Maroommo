package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.BoardServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("게시판 관리")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    public BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @PostMapping
    @ApiOperation("게시판 생성 : json(카테고리 서브 아이디(categorysub_id), 내용(content), 생성시간(createtime), 조회수(views), 사진(picture), 제목(title), 작성자아이디(user_id))")
    public ResponseEntity<?> create(@RequestBody BoardInsertDto insertDto) {
        return ResponseEntity.ok(boardServiceImpl.join(insertDto));
    }

    @DeleteMapping("{id}/{user_id}")
    @ApiOperation("게시판 삭제 : 게시판 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int bid,@PathVariable("user_id") String user_id) {
        String ans = boardServiceImpl.delete(bid,user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping
    @ApiOperation("게시판 수정 : json(수정내용(content), 게시판 아이디(id), 사진(picture), title(제목), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestBody BoardModifyDto modifyDto) {
        return ResponseEntity.ok(boardServiceImpl.update(modifyDto));
    }

    static final int page_num = 5;
    @GetMapping
    @ApiOperation("게시판 조회 (pageable) : 카테고리 서브 아이디(id) + size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호 ")
    public Page<BoardResponseDto> BoardPageable (Model model, @RequestParam("categorySub_id") int categorySub_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return boardServiceImpl.listBoard_Pageable(categorySub_id, pageable);
    }

    @GetMapping("{id}")
    @ApiOperation("게시판 상세조회  : 게시판 아이디(id)")
    public ResponseEntity<?> detail (@PathVariable("id") int bid) {
        return ResponseEntity.status(HttpStatus.OK).body(boardServiceImpl.BoardDetail(bid));
    }


}
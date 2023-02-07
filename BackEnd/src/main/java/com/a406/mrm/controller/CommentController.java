package com.a406.mrm.controller;

import com.a406.mrm.model.dto.BoardModifyDto;
import com.a406.mrm.model.dto.BoardResponseDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import com.a406.mrm.service.CommentServiceImpl;
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

@RestController
@RequestMapping("comment")
@Api("댓글 관리 : ex) 답변 과 동일 (해결, 미해결 차이)")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }


    @PostMapping
    @ApiOperation("댓글 생성 : json(내용(content), 생성시간(createtime), 질문 아이디(board_id), 작성자 아이디(user_id))")
    public ResponseEntity<?> create(@RequestBody CommentInsertDto insertDto) {
        return ResponseEntity.ok(commentServiceImpl.join(insertDto));
    }

    @DeleteMapping("{id}/{user_id}")
    @ApiOperation("댓글 삭제 : 댓글 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int cid, @PathVariable("user_id") String user_id) {
        String ans = commentServiceImpl.delete(cid, user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping
    @ApiOperation("댓글 수정 : json(수정내용(content), 댓글 아이디(id), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestBody CommentModifyDto modifyDto) {
        return ResponseEntity.ok(commentServiceImpl.update(modifyDto));
    }


}
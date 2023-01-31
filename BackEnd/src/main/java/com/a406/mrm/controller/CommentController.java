package com.a406.mrm.controller;

import com.a406.mrm.model.dto.BoardModifyDto;
import com.a406.mrm.model.dto.BoardResponseDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import com.a406.mrm.service.CommentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment/")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }


    @PostMapping(value = "new")
    public ResponseEntity<?> create(@RequestBody CommentInsertDto insertDto, @RequestParam("user_id") String user_id, @RequestParam("board_id") int board_id) {
        CommentInsertDto commentInsertDto = new CommentInsertDto();
        commentInsertDto.setContent(insertDto.getContent());
        commentInsertDto.setCreatetime(insertDto.getCreatetime());
        commentInsertDto.setUser_id(user_id);
        commentInsertDto.setBoard_id(board_id);
        return ResponseEntity.ok(commentServiceImpl.join(commentInsertDto, board_id, user_id));
    }

    @DeleteMapping("delete/{id}/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("id") int cid, @PathVariable("user_id") String user_id) {
        String ans = commentServiceImpl.delete(cid, user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody CommentModifyDto modifyDto, @RequestParam("comment_id") int comment_id, @RequestParam("user_id") String user_id) {
        return ResponseEntity.ok(commentServiceImpl.update(modifyDto, comment_id, user_id));
    }


}
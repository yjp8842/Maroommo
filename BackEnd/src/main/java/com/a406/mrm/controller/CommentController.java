package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.CommentService;
import com.a406.mrm.service.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("comment")
@Api("댓글 관리 : ex) 답변 과 동일 (해결, 미해결 차이)")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * @param insertDto
     *              를 통해 댓글을 생성한다
     * @return newComment : 생성한 댓글을 반환한다
     */
    @PostMapping
    @ApiOperation("댓글 생성 : json(내용(content), 생성시간(createtime), 질문 아이디(board_id), 작성자 아이디(user_id))")
    public ResponseEntity<?> create(@RequestBody CommentInsertDto insertDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        CommentResponseDto commentResponseDto = null;

        try {
            commentResponseDto = commentService.join(insertDto);
            resultMap.put("newComment", commentResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param cid
     * @param user_id
     *              를 통해 댓글을 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @DeleteMapping("{id}")
    @ApiOperation("댓글 삭제 : 댓글 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int cid, @AuthenticationPrincipal User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            boolean isDelete = commentService.delete(cid,user.getUsername());
            resultMap.put("isDelete", isDelete);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param modifyDto
     *              를 통해 댓글을 수정한다
     * @return comment : 수정한 댓글을 반환한다
     */
    @PatchMapping
    @ApiOperation("댓글 수정 : json(수정내용(content), 댓글 아이디(id), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestBody CommentModifyDto modifyDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        CommentModifyDto commentModifyDto = null;

        try {
            commentModifyDto = commentService.update(modifyDto);
            resultMap.put("comment", commentModifyDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


}
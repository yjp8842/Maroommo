package com.a406.mrm.controller;

import com.a406.mrm.model.dto.AnswerInsertDto;
import com.a406.mrm.model.dto.AnswerModifyDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import com.a406.mrm.service.AnswerServiceImpl;
import com.a406.mrm.service.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answer")
@Api("답변 관리 : ex) 댓글 과 동일 (해결, 미해결 차이)")
public class AnswerController {

    private final AnswerServiceImpl answerServiceImpl;

    public AnswerController(AnswerServiceImpl answerServiceImpl) {
        this.answerServiceImpl = answerServiceImpl;
    }


    @PostMapping
    @ApiOperation("카테고리 생성 : json(내용(content), 생성시간(createtime), 질문 아이디(question_id), 작성자 아이디(user_id))")
    public ResponseEntity<?> create(@RequestBody AnswerInsertDto insertDto) {
        return ResponseEntity.ok(answerServiceImpl.join(insertDto));
    }

    @DeleteMapping("{id}/{user_id}")
    @ApiOperation("답변 삭제 : 답변 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int aid, @PathVariable("user_id") String user_id) {
        String ans = answerServiceImpl.delete(aid, user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping
    @ApiOperation("답변 수정 : json(수정내용(content), 답변 아이디(id), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestBody AnswerModifyDto modifyDto) {
        return ResponseEntity.ok(answerServiceImpl.update(modifyDto));
    }


}
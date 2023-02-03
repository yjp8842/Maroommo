package com.a406.mrm.controller;

import com.a406.mrm.model.dto.AnswerInsertDto;
import com.a406.mrm.model.dto.AnswerModifyDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import com.a406.mrm.service.AnswerServiceImpl;
import com.a406.mrm.service.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answer")
public class AnswerController {

    private final AnswerServiceImpl answerServiceImpl;

    public AnswerController(AnswerServiceImpl answerServiceImpl) {
        this.answerServiceImpl = answerServiceImpl;
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnswerInsertDto insertDto) {
        return ResponseEntity.ok(answerServiceImpl.join(insertDto, insertDto.getQuestion_id(), insertDto.getUser_id()));
    }

    @DeleteMapping("{id}/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("id") int aid, @PathVariable("user_id") String user_id) {
        String ans = answerServiceImpl.delete(aid, user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody AnswerModifyDto modifyDto,
                                    @RequestParam("answer_id") int answer_id,
                                    @RequestParam("user_id") String user_id) {
        return ResponseEntity.ok(answerServiceImpl.update(modifyDto, answer_id, user_id));
    }


}
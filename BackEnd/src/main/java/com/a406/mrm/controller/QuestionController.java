package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.QuestionServiceImpl;
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
@RequestMapping("question/")
public class QuestionController {

    private final QuestionServiceImpl questionServiceImpl;

    public QuestionController(QuestionServiceImpl questionServiceImpl) {
        this.questionServiceImpl = questionServiceImpl;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> create(@RequestBody QuestionInsertDto insertDto, @RequestParam("user_id") String user_id, @RequestParam("categorysub_id") int categorysub_id) {
        QuestionInsertDto questionInsertDto = new QuestionInsertDto();
        questionInsertDto.setTitle(insertDto.getTitle());
        questionInsertDto.setContent(insertDto.getContent());
        questionInsertDto.setCreatetime(insertDto.getCreatetime());
        questionInsertDto.setStatus(insertDto.getStatus());
        questionInsertDto.setPicture(insertDto.getPicture());
        questionInsertDto.setUser_id(user_id);
        questionInsertDto.setCategorysub_id(categorysub_id);
        return ResponseEntity.ok(questionServiceImpl.join(questionInsertDto, categorysub_id, user_id));
    }

    @DeleteMapping("delete/{id}/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("id") int qid,@PathVariable("user_id") String user_id) {
        String ans = questionServiceImpl.delete(qid,user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody QuestionModifyDto modifyDto, @RequestParam("question_id") int question_id, @RequestParam("user_id") String user_id) {
        return ResponseEntity.ok(questionServiceImpl.update(modifyDto, question_id, user_id));
    }

    //size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호
    static final int page_num = 3;
    @GetMapping("list")
    public Page<QuestionResponseDto> QuestionPageable (Model model, @RequestParam("categorySub_id") int categorySub_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return questionServiceImpl.listQuestion_Pageable(categorySub_id, pageable);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail (@PathVariable("id") int qid) {
        List<QuestionResponseAnswerDto> result = questionServiceImpl.listQuestion(qid);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.QuestionServiceImpl;
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
@RequestMapping("question")
@Api("질문 관리")
public class QuestionController {

    private final QuestionServiceImpl questionServiceImpl;

    public QuestionController(QuestionServiceImpl questionServiceImpl) {
        this.questionServiceImpl = questionServiceImpl;
    }

    @PostMapping
    @ApiOperation("질문 생성 : json(카테고리 서브 아이디(categorysub_id), 내용(content), 생성시간(createtime), 조회수(views), 상태(status), 사진(picture), 제목(title), 작성자아이디(user_id))")
    public ResponseEntity<?> create(@RequestBody QuestionInsertDto insertDto) {
        return ResponseEntity.ok(questionServiceImpl.join(insertDto));
    }

    @DeleteMapping("{id}/{user_id}")
    @ApiOperation("질문 삭제 : 질문 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int qid,@PathVariable("user_id") String user_id) {
        String ans = questionServiceImpl.delete(qid,user_id);
        if (ans.equals("OK")){
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping
    @ApiOperation("게시판 수정 : json(수정내용(content), 질문 아이디(id), 사진(picture), status(상태), 제목(title), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestBody QuestionModifyDto modifyDto) {
        return ResponseEntity.ok(questionServiceImpl.update(modifyDto));
    }

    @PatchMapping("status")
    @ApiOperation("게시판 상태 수정 : json(질문 아이디(id), status(상태 = 0이면 미해결 1이면 해결), 작성자 아이디(user_id))")
    public ResponseEntity<?> status (@RequestBody QuestionResponseStatusDto modifyDto) {
        return ResponseEntity.ok(questionServiceImpl.status(modifyDto));
    }

    //size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호
    static final int page_num = 10;
    @GetMapping
    @ApiOperation("질문 조회 (pageable) : 카테고리 서브 아이디(id) + size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호 ")
    public Page<QuestionResponseDto> QuestionPageable (Model model, @RequestParam("categorySub_id") int categorySub_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return questionServiceImpl.listQuestion_Pageable(categorySub_id, pageable);
    }

    @GetMapping("{id}")
    @ApiOperation("질문 상세조회  : 질문 아이디(id)")
    public ResponseEntity<?> detail (@PathVariable("id") int qid) {
        return ResponseEntity.status(HttpStatus.OK).body(questionServiceImpl.QuestionDetail(qid));
    }


}
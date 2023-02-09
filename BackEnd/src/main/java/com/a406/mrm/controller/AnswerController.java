package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.AnswerService;
import com.a406.mrm.service.AnswerServiceImpl;
import com.a406.mrm.service.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("answer")
@Api("답변 관리 : ex) 댓글 과 동일 (해결, 미해결 차이)")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    /**
     * @param insertDto
     *          로 새로운 답변 newAnswer를 생성한다
     * @return newAnswer : 답변을 반환한다
     */
    @PostMapping
    @ApiOperation("카테고리 생성 : json(내용(content), 생성시간(createtime), 질문 아이디(question_id), 작성자 아이디(user_id))")
    public ResponseEntity<?> create(@RequestBody AnswerInsertDto insertDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        AnswerResponseDto answerResponseDto = null;

        try {
            answerResponseDto = answerService.join(insertDto);
            resultMap.put("newAnswer", answerResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param aid
     * @param user_id
     *          를 가지고 답변을 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @DeleteMapping("{id}/{user_id}")
    @ApiOperation("답변 삭제 : 답변 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int aid, @PathVariable("user_id") String user_id) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            boolean isDelete = answerService.delete(aid, user_id);

            resultMap.put("isDelete",isDelete);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param modifyDto
     *          를 가지고 답변을 수정한다
     * @return answer : 수정한 답변을 반환한다
     */
    @PatchMapping
    @ApiOperation("답변 수정 : json(수정내용(content), 답변 아이디(id), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestBody AnswerModifyDto modifyDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        AnswerModifyDto answerModifyDto = null;

        try {
            answerModifyDto = answerService.update(modifyDto);
            resultMap.put("answer", answerModifyDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param goodDto
     *              를 받아서 종아요를 1 증가시킨다
     * @return goods : 1만큼 증가시킨 좋아요 개수를 반환한다
     */
    @PatchMapping("good")
    @ApiOperation("답변 좋아요 : 좋아요(good)")
    public ResponseEntity<?> goodPlus(@RequestBody AnswerGoodDto goodDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        int goods = -1;

        try {
            goods = answerService.goodPlus(goodDto);
            resultMap.put("goods", goods);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
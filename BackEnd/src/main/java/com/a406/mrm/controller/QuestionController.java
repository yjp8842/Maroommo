package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.QuestionService;
import com.a406.mrm.service.QuestionServiceImpl;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("question")
@Api("질문 관리")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * @param(title,content,user_id,room_id,picture)
     *              를 통해 질문을 생성한다
     * @return newQuestion : 생성한 질문을 반환한다
     */
    @PostMapping
    @ApiOperation("질문 생성 : RequestParam으로 (title, content, user_id, room_id, picture = 파일)")
    public ResponseEntity<?> create(@RequestParam String title,
                                    @RequestParam String content,
                                    @RequestParam int room_id,
                                    @RequestPart(value="picture", required = false) MultipartFile picture,
                                    @AuthenticationPrincipal User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        QuestionResponseAnswerDto questionResponseAnswerDto = null;

        try {
            questionResponseAnswerDto = questionService.join(title, content, user.getUsername(), room_id, picture);
            resultMap.put("newQuestion", questionResponseAnswerDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param qid
     * @param user_id
     *              를 통해 질문을 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @DeleteMapping("{id}")
    @ApiOperation("질문 삭제 : 질문 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int qid,@AuthenticationPrincipal User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            boolean isDelete = questionService.delete(qid, user.getPassword());
            resultMap.put("isDelete", isDelete);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param (id,content,picture,status,title,user_id)
     *              를 통해 질문을 수정한다
     * @return question : 수정한 질문을 반환한다
     */
    @PostMapping("update")
    @ApiOperation("게시판 수정 : 질문 아이디(id), 수정내용(content), 사진(picture), status(상태), 제목(title), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestParam int id,
                                    @RequestParam String content,
                                    @RequestPart(value="picture", required = false) MultipartFile picture,
                                    @RequestParam int status,
                                    @RequestParam String title,
                                    @AuthenticationPrincipal User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus Hstatus = HttpStatus.OK;
        QuestionResponseAnswerDto questionModifyDto = null;

        try {
            questionModifyDto = questionService.update(id, content, picture, status, title, user.getUsername());
            resultMap.put("question", questionModifyDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            Hstatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, Hstatus);
    }

    /**
     * @param modifyDto
     *              를 통해 해결 상태를 수정한다
     * @return question : 수정한 질문을 반환한다
     */
    @PatchMapping("status")
    @ApiOperation("게시판 상태 수정 : json(질문 아이디(id), status(상태 = 0이면 미해결 1이면 해결), 작성자 아이디(user_id))")
    public ResponseEntity<?> status (@RequestBody QuestionResponseStatusDto modifyDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        QuestionResponseStatusDto questionResponseStatusDto = null;

        try {
            questionResponseStatusDto = questionService.status(modifyDto);
            resultMap.put("question", questionResponseStatusDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    //size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호
    static final int page_num = 10;

    /**
     * @param model
     * @param room_id
     * @param pageable
     *              를 통해 질문 목록을 가져온다
     * @return 페이징 처리된 질문 목록을 반환한다
     */
    @GetMapping
    @ApiOperation("질문 조회 (pageable) : 룸 아이디(id) + size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호 ")
    public Page<QuestionResponseDto> QuestionPageable (Model model, @RequestParam("room_id") int room_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<QuestionResponseDto> page = questionService.listQuestion_Pageable(room_id, pageable);

        return page;
    }

    /**
     * @param qid
     *          를 통해 질문을 조회한다
     * @return question : 조회한 질문의 자세한 정보를 반환한다
     */
    @GetMapping("{id}")
    @ApiOperation("질문 상세조회  : 질문 아이디(id)")
    public ResponseEntity<?> detail (@PathVariable("id") int qid) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        QuestionResponseAnswerDto questionResponseAnswerDto = null;

        try {
            questionResponseAnswerDto = questionService.QuestionDetail(qid);
            resultMap.put("question", questionResponseAnswerDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


}
package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.BoardService;
import com.a406.mrm.service.BoardServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("board")
@Api("게시판 관리")
@RequiredArgsConstructor
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    /**
     * @param (title,content,user_id,categorySub_id,picture)
     *              를 가지고 게시글을 생성한다
     * @return newBoard : 생성한 게시글을 반환한다
     */
    @PostMapping
    @ApiOperation("게시판 생성 : RequestParam으로 (title, content, user_id, room_id, picture = 파일)")
    public ResponseEntity<?> create(@RequestParam String title,
                                    @RequestParam String content,
                                    @RequestParam int room_id,
                                    @RequestPart(value="picture", required = false) MultipartFile picture,
                                    @AuthenticationPrincipal User user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        BoardResponseCommentDto boardResponseCommentDto = null;

        try {
            boardResponseCommentDto = boardService.join(title, content, user.getUsername(), room_id, picture);
            resultMap.put("newBoard", boardResponseCommentDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    /**
     * @param bid
     * @param user_id
     *              를 가지고 게시글을 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @DeleteMapping("{id}")
    @ApiOperation("게시판 삭제 : 게시판 아이디(id), 작성자 아이디(user_id)")
    public ResponseEntity<?> delete(@PathVariable("id") int bid, @AuthenticationPrincipal User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            boolean isDelete = boardService.delete(bid,user.getUsername());
            resultMap.put("isDelete", isDelete);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param (id,content,title,picture,user_id)
     *              를 가지고 게시글을 수정한다
     * @return board : 수정한 게시글의 자세한 정보를 반환한다
     */
    @PostMapping("update")
    @ApiOperation("게시판 수정 : 게시판 아이디(id),, 수정내용(content), title(제목), 사진(picture), 작성자 아이디(user_id))")
    public ResponseEntity<?> update(@RequestParam int id,
                                    @RequestParam String content,
                                    @RequestPart(value="picture", required = false) MultipartFile picture,
                                    @RequestParam String title,
                                    @AuthenticationPrincipal User user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        BoardResponseCommentDto boardModifyDto = null;

        try {
            boardModifyDto = boardService.update(id, content, picture, title, user.getUsername());
            resultMap.put("board", boardModifyDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    static final int page_num = 10;

    // 이 부분은 Page<> 객체를 반환하는데 프론트랑 연동시 잘 동작되는지 확인바람
    /**
     * @param model
     * @param room_id
     * @param pageable
     *              를 가지고 게시글 목록을 가져온다
     * @return page : 페이징 처리가 된 게시글 목록을 반환한다
     */
    @GetMapping
    @ApiOperation("게시판 조회 (pageable) : 룸 아이디(id) + size = 받을 데이터 개수 -> page = 이에 따른 페이지 번호 ")
    public Page<BoardResponseDto> BoardPageable (Model model, @RequestParam("room_id") int room_id,
         @PageableDefault(size = page_num, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<BoardResponseDto> page = boardService.listBoard_Pageable(room_id,pageable);

        return page;
    }

    /**
     * @param bid
     *          를 가지고 게시글을 조회한다
     * @return board : 조회한 게시글의 자세한 정보를 반환한다
     */
    @GetMapping("{id}")
    @ApiOperation("게시판 상세조회  : 게시판 아이디(id)")
    public ResponseEntity<?> detail (@PathVariable("id") int bid) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        BoardResponseCommentDto boardResponseCommentDto = null;

        try {
            boardResponseCommentDto = boardService.BoardDetail(bid);
            resultMap.put("board", boardResponseCommentDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


}

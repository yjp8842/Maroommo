package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.service.CategoryService;
import com.a406.mrm.service.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
@Api("카테고리 관리 : ex) 운영체제, 알고리즘")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * @param insertDto
     *              를 통해 카테고리를 생성합니다
     * @return newCategory : 생성한 카테고리를 반환합니다
     */
    @PostMapping
    @ApiOperation("카테고리 생성 : json(이름(name), 룸 아이디(roomId))")
    public ResponseEntity<?> create(@RequestBody CategoryInsertDto insertDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        CategoryInsertDto categoryInsertDto = null;

        try {
            categoryInsertDto = categoryService.join(insertDto);
            resultMap.put("newCategory", categoryInsertDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    /**
     * @param cid
     *          를 통해 카테고리를 삭제합니다
     * @return isDelete : 삭제 성공 여부를 반환합니다
     */
    @DeleteMapping("{id}")
    @ApiOperation("카테고리 삭제 : 카테고리 아이디(id)")
    public ResponseEntity<?> delete(@PathVariable("id") int cid) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            categoryService.delete(cid);
            resultMap.put("isDelete", true);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param cid
     * @param name
     *          를 통해 카테고리 이름을 수정합니다
     * @return categoryName : 수정한 카테고리의 이름을 반환합니다
     */
    @PatchMapping
    @ApiOperation("카테고리 수정 : 카테고리 아이디(id), 변경하고자 하는 이름(name)")
    public ResponseEntity<?> update(@RequestParam("id") int cid, @RequestParam("name") String name) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        String categoryName = null;

        try {
            categoryName = categoryService.update(cid,name);
            resultMap.put("categoryName", categoryName);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param cid
     *          를 통해 카테고리 목록을 가져옵니다
     * @return categories : 카테고리 목록을 반환합니다
     */
    @GetMapping
    @ApiOperation("카테고리 리스트 출력 : 카테고리 아이디(id) -> 카테고리 정보와 해당 카테고리의 서브 정보")
    public ResponseEntity<?> CategoryList(@RequestParam("id") int cid) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        List<CategoryResponseDto> categoryResponseDtoList = null;

        try {
            categoryResponseDtoList = categoryService.CategoryDetail(cid);
            resultMap.put("categories", categoryResponseDtoList);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.service.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Api("카테고리 관리 : ex) 운영체제, 알고리즘")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping
    @ApiOperation("카테고리 생성 : json(이름(name), 룸 아이디(roomId))")
//    @RequestBody QuestionInsertDto insertDto
    public ResponseEntity<?> create(@RequestBody CategoryInsertDto insertDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryServiceImpl.join(insertDto));
    }


    @DeleteMapping("{id}")
    @ApiOperation("카테고리 삭제 : 카테고리 아이디(id)")
    public ResponseEntity<?> delete(@PathVariable("id") int cid) {
        categoryServiceImpl.delete(cid);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping
    @ApiOperation("카테고리 수정 : 카테고리 아이디(id), 변경하고자 하는 이름(name)")
    public ResponseEntity<?> update(@RequestParam("id") int cid, @RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryServiceImpl.update(cid, name));
    }

    @GetMapping
    @ApiOperation("카테고리 리스트 출력 : 카테고리 아이디(id) -> 카테고리 정보와 해당 카테고리의 서브 정보")
    public ResponseEntity<?> CategoryList(@RequestParam("id") int cid) {
        List<CategoryResponseDto> result = categoryServiceImpl.CategoryDetail(cid);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

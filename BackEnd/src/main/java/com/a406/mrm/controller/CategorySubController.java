package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.service.CategorySubService;
import com.a406.mrm.service.CategorySubServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categorysub")
@Api("카테고리 서브 관리 : ex) 윈도우, 리눅스, 맥")
public class CategorySubController {

    private final CategorySubServiceImpl categorySubServiceImpl;

    @Autowired
    public CategorySubController(CategorySubServiceImpl categorySubServiceImpl) {
        this.categorySubServiceImpl = categorySubServiceImpl;
    }

    @PostMapping
    @ApiOperation("카테고리 서브 생성 : json(카테고리 아이디(category_id), 이름(name), 종류(subtype))")
    public ResponseEntity<?> create(@RequestBody CategorySubInsertDto insertDto) {
        return ResponseEntity.ok(categorySubServiceImpl.join(insertDto));
    }

    @DeleteMapping("{id}")
    @ApiOperation("카테고리 서브 삭제 : 카테고리 서브 아이디(id)")
    public ResponseEntity<?> delete(@PathVariable("id") int cid) {
        categorySubServiceImpl.delete(cid);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping
    @ApiOperation("카테고리 서브 수정 : 카테고리 서브 아이디(id), 변경하고자 하는 이름(name)")
    public ResponseEntity<?> update_name(@RequestParam("id") int cid, @RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categorySubServiceImpl.update_name(cid, name));
    }


}

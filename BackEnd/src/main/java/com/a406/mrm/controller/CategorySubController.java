package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.service.CategorySubService;
import com.a406.mrm.service.CategorySubServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("categorysub")
@Api("카테고리 서브 관리 : ex) 윈도우, 리눅스, 맥")
@RequiredArgsConstructor
public class CategorySubController {

    private final CategorySubService categorySubService;

    /**
     * @param insertDto
     *              를 통해 서브 카테고리를 생성한다
     * @return newCategorySub : 생성한 서브 카테고리를 반환한다
     */
    @PostMapping
    @ApiOperation("카테고리 서브 생성 : json(카테고리 아이디(category_id), 이름(name), 종류(subtype))")
    public ResponseEntity<?> create(@RequestBody CategorySubInsertDto insertDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        CategorySubInsertDto categorySubInsertDto = null;

        try {
            categorySubInsertDto = categorySubService.join(insertDto);
            resultMap.put("newCategorySub", categorySubInsertDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param cid
     *          를 통해 서브 카테고리를 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @DeleteMapping("{id}")
    @ApiOperation("카테고리 서브 삭제 : 카테고리 서브 아이디(id)")
    public ResponseEntity<?> delete(@PathVariable("id") int cid) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        CategorySubInsertDto categorySubInsertDto = null;

        try {
            categorySubService.delete(cid);
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
     *          를 통해 서브 카테고리의 이름을 수정한다
     * @return categorySubName : 수정한 이름을 반환한다
     */
    @PatchMapping
    @ApiOperation("카테고리 서브 수정 : 카테고리 서브 아이디(id), 변경하고자 하는 이름(name)")
    public ResponseEntity<?> update_name(@RequestParam("id") int cid, @RequestParam("name") String name) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        String categorySubName = null;

        try {
            categorySubName = categorySubService.update_name(cid,name);
            resultMap.put("categorySubName", categorySubName);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


}

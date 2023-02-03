package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.service.CategorySubService;
import com.a406.mrm.service.CategorySubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categorysub")
public class CategorySubController {

    private final CategorySubServiceImpl categorySubServiceImpl;

    @Autowired
    public CategorySubController(CategorySubServiceImpl categorySubServiceImpl) {
        this.categorySubServiceImpl = categorySubServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam("name") String name, @RequestParam("subtype") int subtype, @RequestParam("category_id") int category_id) {
        CategorySubInsertDto categorySubInsertDto = new CategorySubInsertDto();
        categorySubInsertDto.setName(name);
        categorySubInsertDto.setSubtype(subtype);
        categorySubInsertDto.setCategory_id(category_id);
        return ResponseEntity.ok(categorySubServiceImpl.join(categorySubInsertDto, category_id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int cid) {
        categorySubServiceImpl.delete(cid);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping
    public ResponseEntity<?> update_name(@RequestParam("id") int cid, @RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categorySubServiceImpl.update_name(cid, name));
    }


}

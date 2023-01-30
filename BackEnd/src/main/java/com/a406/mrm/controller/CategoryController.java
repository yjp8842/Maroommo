package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category/")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

//    @GetMapping(value = "new")
//    public ModelAndView createForm() {
//        ModelAndView mav = new ModelAndView("category/createCategoryForm");
//        return mav;
//    }

    @PostMapping(value = "new")
    public ResponseEntity<?> create(@RequestParam("name") String name, @RequestParam("room_id") int room_id) {
        CategoryInsertDto categoryInsertDto = new CategoryInsertDto();
        categoryInsertDto.setName(name);
        categoryInsertDto.setRoomId(room_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryServiceImpl.join(categoryInsertDto,room_id));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int cid) {
        categoryServiceImpl.delete(cid);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestParam("id") int cid, @RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryServiceImpl.update(cid, name));
    }

    @GetMapping(value = "list")
    public ResponseEntity<?> CategoryList(@RequestParam("room_id") int room_id) {
        List<CategoryResponseDto> result = categoryServiceImpl.listCategory(room_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

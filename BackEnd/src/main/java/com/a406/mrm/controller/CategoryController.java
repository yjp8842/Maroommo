package com.a406.mrm.controller;

import com.a406.mrm.model.dto.CategoryDto;
import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("category/")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "new")
    public ModelAndView createForm() {
        ModelAndView mav = new ModelAndView("category/createCategoryForm");
        return mav;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> create(@RequestParam("name") String name, @RequestParam int roomid) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);
        categoryDto.setRoomId(roomid);
        return ResponseEntity.ok(categoryService.join(categoryDto,roomid));
    }

    @GetMapping(value = "delete")
    public ModelAndView deleteForm() {
        ModelAndView mav = new ModelAndView("category/deleteCategoryForm");
        return mav;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") int cid) {
        categoryService.delete(cid);
    }

    @PatchMapping("update")
    public void update(@RequestParam("id") int cid, @RequestParam("name") String name) {
        categoryService.update(cid, name);
    }

    @GetMapping(value = "list")
    public List<Category> Categorylist(@RequestBody Room room) {
        List<Category> list = categoryService.findCategory(room.getId());
        System.out.println(list);
        return list;
    }
}

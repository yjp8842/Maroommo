package com.a406.mrm.controller;

import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ResponseEntity<?> create(@RequestBody Room room, @RequestParam("name") String name) {

        Category category = new Category();
        category.setRoom(room);
        category.setName(name);
        return ResponseEntity.ok(categoryService.join(category));
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
}

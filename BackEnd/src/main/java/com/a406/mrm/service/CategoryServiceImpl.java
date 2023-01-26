package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.entity.Category;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, RoomRepository roomRepository) {
        this.categoryRepository = categoryRepository;
        this.roomRepository = roomRepository;
    }

    public CategoryInsertDto join(CategoryInsertDto categoryInsertDto, int roomId){
        Category category = new Category(categoryInsertDto, roomRepository.findById(roomId).get());
        return new CategoryInsertDto(categoryRepository.save(category));
    }
    public void delete(int id){
        categoryRepository.deleteById(id);
    }

    public Category update(int id, String name){
        Category category = categoryRepository.findById(id);
        category.setName(name);
        return category;
    }

    public List listCategory(int room_id) { return categoryRepository.findByroom_Id(room_id);}

    public Category findOne(int id) {
        return categoryRepository.findById(id);
    }
}

package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryDto;
import com.a406.mrm.model.entity.Category;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, RoomRepository roomRepository) {
        this.categoryRepository = categoryRepository;
        this.roomRepository = roomRepository;
    }

    public CategoryDto join(CategoryDto categoryDto, int roomId){
        Category category = new Category(categoryDto, roomRepository.findById(roomId));
        return new CategoryDto(categoryRepository.save(category));
    }
    public void delete(int id){
        categoryRepository.deleteById(id);
    }

    public void update(int id, String name){
        Category category = categoryRepository.findById(id);
        category.setName(name);
    }

//    public List<Category> findCategory(int group_id){ return categoryRepository.findByRoom(group_id);}

    public List findCategory(int room_id) { return categoryRepository.findByroom_Id(room_id);}

    public Category findOne(int id) {
        return categoryRepository.findById(id);
    }
}

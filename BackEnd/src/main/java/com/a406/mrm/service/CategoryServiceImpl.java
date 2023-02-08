package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public CategoryInsertDto join(CategoryInsertDto insertDto){
        Category category = new Category(insertDto, roomRepository.findById(insertDto.getRoomId()).get());
        return new CategoryInsertDto(categoryRepository.save(category));
    }
    public void delete(int id){
        categoryRepository.deleteById(id);
    }

    public String update(int id, String name){
        Category category = categoryRepository.findById(id);
        category.setName(name);
        return categoryRepository.save(category).getName();
    }

    public List<CategoryResponseDto> CategoryDetail(int category_id) {
        List<CategoryResponseDto> result = categoryRepository.findCategoryById(category_id)
                .stream()
                .map(x -> new CategoryResponseDto(x)).collect(Collectors.toList());
        return result;
    }

}

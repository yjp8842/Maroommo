package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;

    public CategoryInsertDto join(CategoryInsertDto insertDto) throws Exception{
        Room room = roomRepository.findById(insertDto.getRoomId()).get();
        CategoryInsertDto categoryInsertDto = null;

        if(room != null){
            Category category = new Category(insertDto, room);
            category = categoryRepository.save(category);
            categoryInsertDto = new CategoryInsertDto(category);
        }

        return categoryInsertDto;
    }
    public void delete(int id) throws Exception{
        categoryRepository.deleteById(id);
    }

    public String update(int id, String name) throws Exception{
        Category category = categoryRepository.findById(id);
        String categoryName = null;

        if(category != null){
            category.setName(name);
            category = categoryRepository.save(category);
            categoryName = category.getName();
        }

        return categoryName;
    }

    public List<CategoryResponseDto> CategoryDetail(int category_id) throws Exception{
        List<CategoryResponseDto> result = categoryRepository.findCategoryById(category_id)
                .stream()
                .map(x -> new CategoryResponseDto(x)).collect(Collectors.toList());
        return result;
    }

}

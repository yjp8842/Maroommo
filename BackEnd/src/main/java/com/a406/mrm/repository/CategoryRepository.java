package com.a406.mrm.repository;

import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category save(Category category);

    void deleteById (int id);

    Category findById(int id);

    List<Category> findByroom_Id (@Param("room_id")int room_id);
}

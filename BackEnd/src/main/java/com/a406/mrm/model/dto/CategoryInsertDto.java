package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.CategorySub;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CategoryInsertDto {
    public CategoryInsertDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.roomId = category.getRoom().getId();
    }

    private int id;
    private String name;
    private int roomId;
    private List<CategorySub> categorySubs = new ArrayList<>();
}

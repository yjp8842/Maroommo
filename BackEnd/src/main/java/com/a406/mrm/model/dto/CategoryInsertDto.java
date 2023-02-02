package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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



}
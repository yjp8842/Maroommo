package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.CategorySub;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategorySubInsertDto {

    public CategorySubInsertDto(CategorySub categorySub) {
        this.name = categorySub.getName();
        this.subtype = categorySub.getSubtype();
        this.category_id = categorySub.getCategory().getId();
    }

    private String name;
    private int subtype;
    private int category_id;
}

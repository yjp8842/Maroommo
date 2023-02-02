package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.CategorySub;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto {
    public CategoryResponseDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.roomId = category.getRoom().getId();
        this.categorySubs_id = category.getCategorySubs().stream().map(x -> x.getId()).collect(Collectors.toList());
        this.categorySub_name = category.getCategorySubs().stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    private int id;
    private String name;
    private int roomId;

//    private HashMap<Integer, String> categorySubs = new HashMap<>();
    private List<Integer> categorySubs_id = new ArrayList<>();
    private List<String> categorySub_name = new ArrayList<>();


}

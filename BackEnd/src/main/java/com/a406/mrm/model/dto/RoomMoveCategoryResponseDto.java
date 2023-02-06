package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.TodoTime;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomMoveCategoryResponseDto {
    public RoomMoveCategoryResponseDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }

    private int id;
    private String name;
}

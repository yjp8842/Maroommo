package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity
public class Category {

    public Category(CategoryInsertDto categoryInsertDto, Room room){
        this.name = categoryInsertDto.getName();
        this.room = room;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<CategorySub> categorySubs = new ArrayList<>();
}

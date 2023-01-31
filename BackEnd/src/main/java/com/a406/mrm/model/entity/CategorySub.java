package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorysub")
@Entity
public class CategorySub {
    public CategorySub(CategorySubInsertDto categorySubInsertDto, Category category){
        this.name = categorySubInsertDto.getName();
        this.subtype = categorySubInsertDto.getSubtype();
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int subtype;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "categorySub", cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();
}

package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.dto.BoardModifyDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
@Entity
public class Board {
    public Board(BoardInsertDto boardInsertDto, CategorySub categorySub, User user) {
        this.title = boardInsertDto.getTitle();
        this.content = boardInsertDto.getContent();
        this.createTime = boardInsertDto.getCreatetime();
        this.hit = boardInsertDto.getHit();
        this.picture = boardInsertDto.getPicture();
        this.categorySub = categorySub;
        this.user = user;
    }

    public Board(BoardModifyDto boardModifyDto, int board_id) {
        this.id = board_id;
        this.title = boardModifyDto.getTitle();
        this.content = boardModifyDto.getContent();
        this.picture = boardModifyDto.getPicture();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private int hit;
    private String picture;

    @ManyToOne(targetEntity = CategorySub.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "categorySub_id")
    @JsonBackReference
    private CategorySub categorySub;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


}
package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.dto.BoardModifyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        this.createTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.views = 0;
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
    private LocalDateTime createTime;
    private int views;
    private String picture;

    @ManyToOne(targetEntity = CategorySub.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "categorySub_id")
    private CategorySub categorySub;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();


}
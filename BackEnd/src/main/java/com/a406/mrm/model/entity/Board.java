package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.BoardModifyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
@Entity
public class Board {

    public Board(String title, String content, String picture, User user, Room room) {
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.user = user;
        this.room = room;
        this.views = 0;
        this.createTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
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

    @ManyToOne(targetEntity = Room.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();


}
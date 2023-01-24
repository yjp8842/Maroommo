package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.TodoRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@ApiModel("Todo Not schedule ")
@Table(name = "todo")
@NoArgsConstructor
public class Todo {
    public Todo(TodoRequestDto todoRequestDto, User user){
        this.content = todoRequestDto.getContent();
        this.startTime = todoRequestDto.getStartTime();
        this.state = 0;
        this.user = user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("todo id")
    private int id;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(nullable = false)// sql default 0 필요, 일단 여기서 0으로 초기화
    private int state;

    //room이랑 ManyToMany로 엮일 필요가 없는듯?! -> user랑 이미 ManyToMany
    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<TodoHasRoom> rooms = new ArrayList<>();
    @OneToMany(mappedBy="todo", cascade = CascadeType.REMOVE)
    private List<TodoTag> todoTags = new ArrayList<>();

    @OneToMany(mappedBy="todo", cascade = CascadeType.REMOVE)
    private List<TodoTime> todoTimes = new ArrayList<>();

    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
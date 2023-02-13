package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.RoomRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@ApiModel("RoomEntity : Room(=group) information")
@NoArgsConstructor
@Table(name = "room")
public class Room {

    public Room(RoomRequestDto roomRequestDto, String profile){
        this.name = roomRequestDto.getName();
        this.intro = roomRequestDto.getIntro();
        this.profile = profile;
    }

    @ApiModelProperty("room ID, auto increment")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ApiModelProperty("room name")
    @Column(nullable = false)
    private String name;
    @ApiModelProperty("room profile img, it's usually s3 image url ")
    @Column(nullable = true)
    private String profile;
    @ApiModelProperty("one-line introduction")
    @Column(nullable = true)
    private String intro;
    @ApiModelProperty("room Entrance code")
    @Column(nullable = false)
    private String code;

    @ApiModelProperty("User - Room relation table mapping")
    @OneToMany(mappedBy = "room", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<UserHasRoom> users = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.ALL})
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade ={CascadeType.REMOVE})
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.ALL})
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.ALL})
    private List<Question> questions = new ArrayList<>();
}
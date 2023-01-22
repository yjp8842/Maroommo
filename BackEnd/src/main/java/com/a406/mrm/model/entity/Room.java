package com.a406.mrm.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@ApiModel("RoomEntity : Room(=group) information")
@Table(name = "room")
public class Room {
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
    @ApiModelProperty("room public memo")
    @Column(nullable = true)
    private String memo;

    @ApiModelProperty("User - Room relation table mapping")
    @OneToMany(mappedBy = "room", cascade = {CascadeType.ALL})
    private List<UserHasRoom> users = new ArrayList<>();


}
package com.a406.mrm.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity{
    @Id
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = true)
    private String profile;
    @Column(nullable = true)
    private String intro;
    @Column(nullable = true)
    private String memo;
    @Column(nullable = true)
    private String roles; // ROLE_USER, ROLE_ADMIN
    @Column(nullable = true)
    private String provider;
    @Column(nullable = true)
    private String providerId;


    @ApiModelProperty("User - Room relation table mapping")
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserHasRoom> rooms = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Todo> todos = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();
}


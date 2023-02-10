package com.a406.mrm.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
    private String roles; // ROLE_USER, ROLE_ADMIN
    @Column(nullable = true)
    private String provider;
    @Column(nullable = true)
    private String providerId;
}


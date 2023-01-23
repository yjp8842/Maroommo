package com.a406.mrm.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    // cascade 설정 안하면 FK로 쓰는 객체가 아직 저장이 안 되서 오류가 나서 설정
    // 그런데 해당 설정하면 insert는 정상으로 작동하는데 room도 추가로 만들어버림 -> 수정 필요
    @JoinColumn(name = "group_id")
    private Room room;




}

package com.a406.mrm.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;

@Entity
@ApiModel("Todo Tag")
@Getter
@Setter
@ToString
    @NoArgsConstructor
    @Table(name = "todo_tag")
    public class TodoTag {
    public TodoTag(String name, Todo todo){
        this.name = name;
        this.todo = todo;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(targetEntity = Todo.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_id")
    private Todo todo;
}
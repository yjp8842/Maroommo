package com.a406.mrm.model.entity;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo_time")
@ApiModel("Todo Time Check")
public class TodoTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date startTime;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date endTime;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date totalTime;

    @ManyToOne(targetEntity = Todo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
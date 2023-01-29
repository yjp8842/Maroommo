package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.repository.UserRepository;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {
    public Schedule(ScheduleRequestDto scheduleRequestDto, User user, Room room){
        this.user = user;
        this.room = room;
        this.content = scheduleRequestDto.getContent();
        this.startTime = scheduleRequestDto.getStartTime();
        this.endTime = scheduleRequestDto.getEndTime();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date endTime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Room.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
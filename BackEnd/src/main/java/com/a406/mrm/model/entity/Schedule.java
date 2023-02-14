package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.ScheduleRequestDto;
import com.a406.mrm.repository.UserRepository;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "schedule")
public class Schedule {
    public Schedule(ScheduleRequestDto scheduleRequestDto, User user, Room room){
        this.user = user;
        this.room = room;
        this.content = scheduleRequestDto.getContent();
        String month = String.format("%02d",scheduleRequestDto.getMonth());
        String day = String.format("%02d",scheduleRequestDto.getDay());
        this.startTime = ""+scheduleRequestDto.getYear()+"-"+month+"-"+day;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String startTime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Room.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
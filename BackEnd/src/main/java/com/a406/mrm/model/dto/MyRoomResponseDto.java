package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// 마이 페이지를 클릭했을 시 반환해주는 Dto

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyRoomResponseDto {

    private List<RoomListResponseDto> myRooms = new ArrayList<>();

    public MyRoomResponseDto(User user){
        this.myRooms = user.getRooms()
                            .stream()
                            .map(x->new RoomListResponseDto(x)).collect(Collectors.toList());
    }
}

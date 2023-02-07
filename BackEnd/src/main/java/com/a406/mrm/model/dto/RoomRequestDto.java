package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoomRequestDto {
    public RoomRequestDto(String intro, String name){
        this.intro = intro;
        this.name = name;
    }
    private String name;
    private String intro;

}

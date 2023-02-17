package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class RoomDto {
    private String intro ;
    private String name;
    private MultipartFile profile;

    public RoomDto(String intro, String name, MultipartFile profile){
        this.intro = intro;
        this.name = name;
        this.profile = profile;
    }

}

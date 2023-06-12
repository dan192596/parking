package com.project.parking.data.dto;

import com.project.parking.data.entity.Chat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChatDTO {

    public ChatDTO(Chat chat){
        this.setId(chat.getId());
        this.setParking(new ParkingDTO(chat.getParking()));
        this.setUser(chat.getUser().getId());
        this.setCreated(chat.getCreatedDatetime());
        this.setParkingId(chat.getParking().getId());
    }

    private Long id;
    private ParkingDTO parking;
    private Long parkingId;
    private Long user;
    private Date created;
}

package com.project.parking.data.dto;

import com.project.parking.data.entity.Chat;
import lombok.Data;

import java.util.Date;

@Data
public class ChatDTO {

    public ChatDTO(Chat chat){
        this.setId(chat.getId());
        this.setParking(new ParkingDTO(chat.getParking()));
        this.setUser(new UserDTO(chat.getUser()));
        this.setCreated(chat.getCreatedDatetime());
    }

    private Long id;
    private ParkingDTO parking;
    private UserDTO user;
    private Date created;
}

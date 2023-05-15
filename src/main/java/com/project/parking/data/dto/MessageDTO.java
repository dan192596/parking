package com.project.parking.data.dto;

import com.project.parking.data.entity.Message;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    public MessageDTO(Message message){
        this.setId(message.getId());
        this.setMessage(message.getMessage());
        this.setSentDatetime(message.getSentDatetime());
        this.setChat(message.getChat().getId());
        this.setUserMessage(message.getUserMessage());
    }

    private Long id;
    private String message;
    private Date sentDatetime;
    private Long chat;
    private Boolean userMessage;
}

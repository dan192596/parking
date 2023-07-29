package com.project.parking.data.dto;

import com.project.parking.data.entity.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class MessageDTO {
    public MessageDTO(Message message){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setId(message.getId());
        this.setMessage(message.getMessage());
        //this.setSentDatetime(message.getSentDatetime());
        this.setSentDatetime(format.format(message.getSentDatetime()));
        this.setChat(message.getChat().getId());
        this.setUserMessage(message.getUserMessage());
    }

    private Long id;
    private String message;
    private String sentDatetime;
    private Long chat;
    private Boolean userMessage;
}

package com.project.parking.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewChatModel {

    private String message;
    private Long parking;
    private Long user;

}

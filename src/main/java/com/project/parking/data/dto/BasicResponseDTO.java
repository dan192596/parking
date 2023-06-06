package com.project.parking.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasicResponseDTO {

    public BasicResponseDTO(String message){
        this.setMessage(message);
    }
    String message;
}

package com.project.parking.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasicTokenModel {

    public BasicTokenModel(Long userId){
        this.setUserId(userId);
    }

    String token;
    Long userId;
}

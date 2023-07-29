package com.project.parking.data.dto;

import com.project.parking.data.entity.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class TokenDTO {

    public TokenDTO(Token token){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setToken(token.getToken());
        //this.setCreatedDatetime(token.getCreatedDatetime());
        this.setCreatedDatetime(format.format(token.getCreatedDatetime()));
        this.setStatus(new StatusDTO(token.getStatus()));
        this.setValidationAttempts(token.getValidationAttempts());
    }

    private String token;
    private String createdDatetime;
    private StatusDTO status;
    private Integer validationAttempts;
}

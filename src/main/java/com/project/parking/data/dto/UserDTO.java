package com.project.parking.data.dto;

import com.project.parking.data.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO {

    public UserDTO(User user){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setId(user.getId());
        this.setName(user.getName());
        this.setLastname(user.getLastname());
        this.setDocument(user.getDocument());
        this.setBirthday(user.getBirthday());
        this.setPhone(user.getPhone());
        this.setEmail(user.getEmail());
        //this.setRegisterDatetime(user.getRegisterDatetime());
        this.setRegisterDatetime(format.format(user.getRegisterDatetime()));
        //this.setUpdatedDatetime(user.getUpdatedDatetime());
        this.setUpdatedDatetime(format.format(user.getUpdatedDatetime()));
        this.setStatus(new StatusDTO(user.getStatus()));
        this.setUuid(user.getIdentifier());
    }

    private Long id;
    private String name;
    private String lastname;
    private String document;
    private Date birthday;
    private String phone;
    private String email;
    private String registerDatetime;
    private String updatedDatetime;
    private String uuid;
    private StatusDTO status;
}

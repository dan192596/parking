package com.project.parking.data.dto;

import com.project.parking.data.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    public UserDTO(User user){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setLastname(user.getLastname());
        this.setDocument(user.getDocument());
        this.setBirthday(user.getBirthday());
        this.setPhone(user.getPhone());
        this.setEmail(user.getEmail());
        this.setRegisterDatetime(user.getRegisterDatetime());
        this.setUpdatedDatetime(user.getUpdatedDatetime());
        this.setStatus(new StatusDTO(user.getStatus()));
    }

    private Long id;
    private String name;
    private String lastname;
    private String document;
    private Date birthday;
    private String phone;
    private String email;
    private Date registerDatetime;
    private Date updatedDatetime;
    private StatusDTO status;
    //Falta Status
}

package com.project.parking.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.parking.data.entity.BankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class BankAccountDTO {

    public BankAccountDTO(BankAccount bankAccount){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setId(bankAccount.getId());
        this.setNumber(bankAccount.getNumber());
        this.setPrincipal(bankAccount.getPrincipal());
        //this.setAffiliation(bankAccount.getAffiliationDatetime());
        this.setAffiliation(format.format(bankAccount.getAffiliationDatetime()));
        this.setStatus(new StatusDTO(bankAccount.getStatus()));
        this.setUser(bankAccount.getUser().getId());
        this.setType(bankAccount.getType());

    }

    private Long id;
    private String number;
    private String type;
    private Boolean principal;
    private String affiliation;
    private StatusDTO status;
    private Long user;

}

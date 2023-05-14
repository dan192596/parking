package com.project.parking.data.dto;

import com.project.parking.data.entity.BankAccount;
import lombok.Data;

import java.util.Date;

@Data
public class BankAccountDTO {

    public BankAccountDTO(BankAccount bankAccount){
        this.setId(bankAccount.getId());
        this.setNumber(bankAccount.getNumber());
        this.setPrincipal(bankAccount.getPrincipal());
        this.setAffiliation(bankAccount.getAffiliationDatetime());
        this.setStatus(new StatusDTO(bankAccount.getStatus()));
        this.setUser(bankAccount.getUser().getId());
    }

    private Long id;
    private String number;
    private String type;
    private Boolean principal;
    private Date affiliation;
    private StatusDTO status;
    private Long user;

}

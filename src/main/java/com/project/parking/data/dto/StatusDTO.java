package com.project.parking.data.dto;

import com.project.parking.data.entity.Status;
import lombok.Data;

@Data
public class StatusDTO {

    public StatusDTO (Status status){
        this.setId(status.getId());
        this.setDescription(status.getDescription());
    }

    private Long id;
    private String description;
}

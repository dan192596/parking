package com.project.parking.data.dto;

import com.project.parking.data.entity.Status;
import com.project.parking.data.entity.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDTO {

    public VehicleDTO(Vehicle vehicle){
        this.setId(vehicle.getId());
        this.setPlate(vehicle.getPlate());
        this.setColor(vehicle.getColor());
        this.setBrand(vehicle.getBrand());
        this.setLine(vehicle.getLine());
        this.setModel(vehicle.getModel());
        this.setStatus(new StatusDTO(vehicle.getStatus()));
        this.setUser(vehicle.getUser().getId());
    }

    private Long id;
    private String plate;
    private String color;
    private String brand;
    private String line;
    private Integer model;
    private StatusDTO status;
    private Long user;
}

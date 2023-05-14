package com.project.parking.data.dto;

import com.project.parking.data.entity.Parking;
import lombok.Data;

@Data
public class ParkingDTO {
    public ParkingDTO(Parking parking){
        this.setId(parking.getId());
        this.setLocation(parking.getLocation());
        this.setLatitude(parking.getLatitude());
        this.setLongitude(parking.getLongitude());
        this.setStatus(new StatusDTO(parking.getStatus()));
    }

    private Long id;
    private String location;
    private Float latitude, longitude;
    private Float priceHour;
    private StatusDTO status;
}

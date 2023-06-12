package com.project.parking.data.dto;

import com.project.parking.data.entity.Parking;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParkingDTO {
    public ParkingDTO(Parking parking){
        this.setId(parking.getId());
        this.setLocation(parking.getLocation());
        this.setLatitude(parking.getLatitude());
        this.setLongitude(parking.getLongitude());
        this.setPriceHour(parking.getPrice_hour());
        this.setStatus(new StatusDTO(parking.getStatus()));
        this.setPriceHour(parking.getPrice_hour());
    }

    private Long id;
    private String location;
    private Float latitude, longitude;
    private Float priceHour;
    private StatusDTO status;
}

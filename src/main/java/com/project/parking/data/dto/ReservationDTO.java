package com.project.parking.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.parking.data.entity.Reservation;
import com.project.parking.data.entity.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReservationDTO {

    public ReservationDTO(Reservation reservation){
        this.setId(reservation.getId());
        this.setStart(reservation.getStartDatetime());
        this.setEnd(reservation.getEndDatetime());
        this.setParking(new ParkingDTO(reservation.getParking()));
        this.setVehicle(new VehicleDTO(reservation.getVehicle()));
        this.setStatus(new StatusDTO(reservation.getStatus()));
    }

    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date start, end;
    private ParkingDTO parking;
    private VehicleDTO vehicle;
    private StatusDTO status;

}

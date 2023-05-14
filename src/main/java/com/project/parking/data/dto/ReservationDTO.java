package com.project.parking.data.dto;

import com.project.parking.data.entity.Reservation;
import com.project.parking.data.entity.Vehicle;
import lombok.Data;

import java.util.Date;

@Data
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
    private Date start, end;
    private ParkingDTO parking;
    private VehicleDTO vehicle;
    private StatusDTO status;

}

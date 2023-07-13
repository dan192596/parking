package com.project.parking.data.dto;

import com.project.parking.data.entity.ReservationPayment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationPaymentDTO {

    public ReservationPaymentDTO(ReservationPayment reservationPayment){
        this.setId(reservationPayment.getId());
        this.setTotal(reservationPayment.getTotal());
        this.setStatus(new StatusDTO(reservationPayment.getStatus()));
        this.setReservation(reservationPayment.getReservation().getId());
        this.setClient(reservationPayment.getBankAccount().getUser().getId());
    }

    private Long id;
    private Float total;
    private StatusDTO status;
    private Long reservation;
    private Long client;
}

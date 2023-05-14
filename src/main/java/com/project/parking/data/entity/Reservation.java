package com.project.parking.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation", schema = "core", catalog = "core")
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_datetime")
    private Date startDatetime;

    @NotNull
    @Column(name = "end_datetime")
    private Date endDatetime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

}

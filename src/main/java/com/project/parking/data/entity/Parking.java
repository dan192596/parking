package com.project.parking.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "parking", schema = "core", catalog = "core")
public class Parking {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "location")
    private String location;

    @NotNull
    @Column(name = "latitude")
    private Float latitude;

    @NotNull
    @Column(name = "longitude")
    private Float longitude;

    @NotNull
    @Column(name = "price_hour")
    private Float price_hour;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}

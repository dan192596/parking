package com.project.parking.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "vehicle", schema = "core", catalog = "core")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "plate")
    private String plate;

    @NotNull
    @Column(name = "color")
    private String color;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "line")
    private String line;

    @NotNull
    @Column(name = "model")
    private Integer model;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}

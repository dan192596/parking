package com.project.parking.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "user", schema = "core", catalog = "core")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "document")
    private String document;

    @NotNull
    @Column(name = "birthday")
    private Date birthday;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Email
    @Column(name = "email")
    private String  email;

    @NotNull
    @Column(name = "identifier")
    private String identifier;

    @Generated(GenerationTime.INSERT)
    @Column(name = "register_datetime")
    private Date registerDatetime;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_datetime")
    private Date updatedDatetime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

}

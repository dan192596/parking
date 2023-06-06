package com.project.parking.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "token", schema = "core", catalog = "core")
public class Token {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "token")
    private String token;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_datetime")
    private Date createdDatetime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Generated(GenerationTime.INSERT)
    @Column(name = "validation_attempts")
    private Integer validationAttempts;

}

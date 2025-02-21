package com.fibbo.spring_app.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_seat")
@Data
public class Seat {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "seat_dh" , nullable = false, updatable = false)
    private LocalDateTime contatoDhCad = LocalDateTime.now();


    @Enumerated(EnumType.STRING)
    private StatusSeat status;


    public enum StatusSeat {
        DISPONÍVEL,
        RESERVADO
    }
}

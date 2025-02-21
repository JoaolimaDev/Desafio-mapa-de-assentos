package com.fibbo.spring_app.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "booking_dh", nullable = false, updatable = false)
    private LocalDateTime contatoDhCad = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusBooking status;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "tb_booking_seat",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats = new ArrayList<>();

    public enum StatusBooking {
        ABERTA,
        ENCERRADA
    }
}

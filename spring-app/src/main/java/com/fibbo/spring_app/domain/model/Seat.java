package com.fibbo.spring_app.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
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
   

    private Boolean ocupada;    

}

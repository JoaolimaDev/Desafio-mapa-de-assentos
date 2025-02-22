package com.fibbo.spring_app.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fibbo.spring_app.domain.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID>{


}

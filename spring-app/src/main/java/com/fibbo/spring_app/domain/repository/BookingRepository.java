package com.fibbo.spring_app.domain.repository;

import org.springframework.stereotype.Repository;

import com.fibbo.spring_app.domain.model.Booking;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID>{
    
}

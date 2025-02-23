package com.fibbo.spring_app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fibbo.spring_app.domain.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    
}

package com.fibbo.spring_app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fibbo.spring_app.domain.model.Booking;

@RepositoryRestResource(path = "booking")
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    
}

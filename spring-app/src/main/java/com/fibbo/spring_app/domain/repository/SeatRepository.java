package com.fibbo.spring_app.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.fibbo.spring_app.domain.model.Seat;

@RepositoryRestResource(path = "seat")
public interface SeatRepository extends JpaRepository<Seat, UUID>{

    
    
}

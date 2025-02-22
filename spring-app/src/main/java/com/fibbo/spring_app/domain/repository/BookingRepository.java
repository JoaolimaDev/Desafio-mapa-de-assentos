package com.fibbo.spring_app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.fibbo.spring_app.domain.model.Booking;

@RepositoryRestResource(path = "booking")
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    
    @Override
    @RestResource(exported = false)
    <S extends Booking> S save(S entity);
}

package com.fibbo.spring_app.domain.repository;

import org.springframework.stereotype.Repository;

import com.fibbo.spring_app.domain.model.Booking;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;



@Repository
@RepositoryRestResource(exported = true, path = "booking")
public interface BookingRepository extends JpaRepository<Booking, UUID>{

    @Override
    @RestResource(exported = false)
    <S extends Booking> S save(S entity);
}

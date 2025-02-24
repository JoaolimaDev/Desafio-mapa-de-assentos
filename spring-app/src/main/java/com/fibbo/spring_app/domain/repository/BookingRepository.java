package com.fibbo.spring_app.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.domain.model.Seat;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findBySeatAndAcao(Seat seat, Booking.StatusBooking acao);
    
}

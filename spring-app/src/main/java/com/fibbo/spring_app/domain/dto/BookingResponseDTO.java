package com.fibbo.spring_app.domain.dto;

import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.domain.model.Seat;


public record BookingResponseDTO(Long id, UserDTO user, Seat seat, String acao) {
    public BookingResponseDTO(Booking booking) {
        this(booking.getId(), new UserDTO(booking.getUser()), booking.getSeat(), booking.getAcao().name());
    }
}


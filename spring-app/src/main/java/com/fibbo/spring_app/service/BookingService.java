package com.fibbo.spring_app.service;


import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;

public interface BookingService {

    public Booking createBooking(BookingDTO bookingRequest);
    public Booking updateBooking(String uuid, String status);
}

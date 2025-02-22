package com.fibbo.spring_app.service;

import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;

public interface BookingService {

    public Booking updateBooking(String id, BookingDTO bookingRequest);
    public Booking createBooking(BookingDTO bookingRequest);
    
}

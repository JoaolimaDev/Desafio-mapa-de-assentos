package com.fibbo.spring_app.service;

import java.util.List;

import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;
import jakarta.servlet.http.HttpServletRequest;

public interface BookingService {

    public Booking updateBooking(HttpServletRequest request, String id, BookingDTO bookingRequest);
    public Booking createBooking(HttpServletRequest request, BookingDTO bookingRequest);
    public List<Booking> listBookings();
    public Booking getBookingById(String id);
    

}

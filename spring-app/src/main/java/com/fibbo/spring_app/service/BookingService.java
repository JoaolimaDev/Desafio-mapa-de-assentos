package com.fibbo.spring_app.service;

import org.springframework.data.domain.Page;

import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;
import jakarta.servlet.http.HttpServletRequest;

public interface BookingService {

    public Booking updateBooking(HttpServletRequest request, String id, BookingDTO bookingRequest);
    public Booking createBooking(HttpServletRequest request, BookingDTO bookingRequest);
    public Page<Booking> listBookings(int page, int size);
    public Booking getBookingById(String id);
    public void deleteBooking(String id);
    

}

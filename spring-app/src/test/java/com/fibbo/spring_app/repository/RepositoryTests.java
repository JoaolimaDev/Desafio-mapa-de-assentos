package com.fibbo.spring_app.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.domain.repository.SeatRepository;
import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.domain.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
class RepositoryTests {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private BookingRepository bookingRepository;

    private Seat seat;
    private Booking booking;

    @BeforeEach
    void setUp() {
        seat = new Seat();
        seat.setId(UUID.randomUUID());
        seat.setOcupada(false);

        booking = new Booking();
        booking.setId(1L);
        booking.setSeat(seat);
    }

    @Test
    void shouldFindSeatById() {
        when(seatRepository.findById(seat.getId())).thenReturn(Optional.of(seat));
        Optional<Seat> foundSeat = seatRepository.findById(seat.getId());
        assertTrue(foundSeat.isPresent());
        assertEquals(seat.getId(), foundSeat.get().getId());
    }

    @Test
    void shouldFindBookingById() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        Optional<Booking> foundBooking = bookingRepository.findById(booking.getId());
        assertTrue(foundBooking.isPresent());
        assertEquals(booking.getId(), foundBooking.get().getId());
    }
}

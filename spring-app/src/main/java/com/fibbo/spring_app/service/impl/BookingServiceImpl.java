package com.fibbo.spring_app.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fibbo.spring_app.config.ExceptionHandler.CustomException;
import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.domain.model.User;
import com.fibbo.spring_app.domain.repository.BookingRepository;
import com.fibbo.spring_app.domain.repository.SeatRepository;
import com.fibbo.spring_app.domain.repository.UserRepository;
import com.fibbo.spring_app.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    public final BookingRepository bookingRepository;
    public final UserRepository userRepository;
    public final SeatRepository seatRepository;

    @Override
    public Booking updateBooking(String id, BookingDTO bookingRequest) { 

        User user = userRepository.findByUsername(bookingRequest.userName())
        .orElseThrow(() -> new CustomException("Usuário inválido!", HttpStatus.BAD_REQUEST));

        Booking booking = bookingRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new CustomException("Nenhuma reserva encontrada para o id: " + id
        , HttpStatus.BAD_REQUEST));

        if (!booking.getUser().equals(user)) {
            
            throw new CustomException("O usuário enviado diverge da reserva", HttpStatus.FORBIDDEN);
        }

        booking.setAcao(Booking.StatusBooking.valueOf(bookingRequest.acao()));

        if (bookingRequest.acao().contains("ALOCACAO")) {
            
            booking.getSeat().setOcupada(true);
        }else{

            booking.getSeat().setOcupada(false);
        }
        

        return bookingRepository.save(booking);
    }

    @Override
    public Booking createBooking(BookingDTO bookingRequest) {


        User user = userRepository.findByUsername(bookingRequest.userName())
        .orElseThrow(() -> new CustomException("Usuário inválido!", HttpStatus.BAD_REQUEST));

        Seat seat = seatRepository.findById(UUID.fromString(bookingRequest.seatId()))
        .orElseThrow(() -> new CustomException("Nenhum assento disponível para o id enviado! " + bookingRequest.seatId(),
        HttpStatus.BAD_REQUEST));

        if (seat.getOcupada()) {
            
            throw new CustomException("O assento" + bookingRequest.seatId() + 
            "já se encontra ocupado! ", HttpStatus.BAD_REQUEST);
        }

        seat.setOcupada(true);


        Booking booking = new Booking();
        booking.setUser(user);
        booking.setAcao(Booking.StatusBooking.valueOf(bookingRequest.acao()));
        booking.setSeat(seat);
        

        return bookingRepository.save(booking);
    }

 
    
}

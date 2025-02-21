package com.fibbo.spring_app.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.dto.BookingUpdateDTO;
import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.service.BookingService;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
@Tag(name="booking-entity-controller")
public class BookingController {
    
    
    public final BookingService bookingService;


    @PostMapping("/")
    public ResponseEntity<Booking> createBooking(
        @RequestBody @Valid BookingDTO bookingRequest
    ){

        Booking booking = bookingService.createBooking(bookingRequest);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/booking/{id}")  
        .buildAndExpand(booking.getId()) 
        .toUriString();

        return ResponseEntity.created(URI.create(uri)).body(booking);

    }


    @PutMapping("/")
    public ResponseEntity<Booking> updateBooking(@RequestParam String uuid, @RequestBody BookingUpdateDTO status){

        Booking booking = bookingService.updateBooking(uuid, status.status());

        return ResponseEntity.ok().body(booking);
    }
}

package com.fibbo.spring_app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.dto.BookingResponseDTO;
import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.service.BookingService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/booking/")
@Tag(name = "booking-entity-controller")
@RequiredArgsConstructor
public class BookingController {

    public final BookingService bookingService;

    @PostMapping("/")
    public ResponseEntity<BookingResponseDTO> createBooking(HttpServletRequest request,
     @RequestBody @Valid BookingDTO bookingRequest){

        Booking booking = bookingService.createBooking(request, bookingRequest);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/booking/{id}")  
        .buildAndExpand(booking.getId()) 
        .toUriString();


        BookingResponseDTO response = new BookingResponseDTO(booking);

        return ResponseEntity.created(URI.create(uri)).body(response);
    }
    

    @GetMapping("/")
    public  ResponseEntity<List<BookingResponseDTO>> listBookings(){


        List<Booking> bookings = bookingService.listBookings();
        List<BookingResponseDTO> bookingResponseDTOs = new ArrayList<>();

        for (Booking booking : bookings) {  

            BookingResponseDTO bookingResponseDTO = new BookingResponseDTO(booking);
            
            bookingResponseDTOs.add(bookingResponseDTO);
        }

        
        return ResponseEntity.ok().body(bookingResponseDTOs);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable String id){

        Booking booking = bookingService.getBookingById(id);
        BookingResponseDTO response = new BookingResponseDTO(booking);
        return ResponseEntity.ok().body(response);
    }



    @PutMapping("/")
    public ResponseEntity<BookingResponseDTO> updateBooking(
    HttpServletRequest request,
    @RequestParam String id, 
    @RequestBody BookingDTO bookingRequest){

        Booking booking = bookingService.updateBooking(request, id, bookingRequest);
        BookingResponseDTO response = new BookingResponseDTO(booking);


        return ResponseEntity.ok().body(response);
    }
}

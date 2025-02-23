package com.fibbo.spring_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.service.SeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seat/")
@RequiredArgsConstructor
public class SeatController {

    public final SeatService seatService;


    @GetMapping("/")
    public List<Seat> getSeats(){

        return seatService.getSeats();
    }
    
}

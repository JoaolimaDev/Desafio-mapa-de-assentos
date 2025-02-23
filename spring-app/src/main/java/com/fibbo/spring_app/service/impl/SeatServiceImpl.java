package com.fibbo.spring_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.domain.repository.SeatRepository;
import com.fibbo.spring_app.service.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService{


    public final SeatRepository seatRepository;

    @Override
    public List<Seat> getSeats() {
        
        return seatRepository.findAll();
    }
    
}

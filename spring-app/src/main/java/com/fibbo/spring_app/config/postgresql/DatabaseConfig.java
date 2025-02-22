package com.fibbo.spring_app.config.postgresql;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.domain.model.User;
import com.fibbo.spring_app.domain.repository.SeatRepository;
import com.fibbo.spring_app.domain.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        if (userRepository.findByUsername("user1" ).isEmpty() && userRepository.findByUsername("user2" ).isEmpty()) {
            
            User admin = new User();
            admin.setUsername("user1");
            admin.setPassword(passwordEncoder.encode("user123"));
            admin.setRole("ADMIN");

            User user = new User();
            user.setUsername("user2");
            user.setPassword(passwordEncoder.encode("user1234"));
            user.setRole("USER");

            
            userRepository.saveAll(Arrays.asList(admin, user));

        }

        List<Seat> seats = seatRepository.findAll();
     
        if (seats.size() < 15) {
            for (int i = 0; i < 15 - seats.size(); i++) {
                Seat seat = new Seat();
                
                seat.setOcupada(false);
                seatRepository.save(seat);
            }
        }

    }
}
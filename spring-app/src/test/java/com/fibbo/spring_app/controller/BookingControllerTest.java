package com.fibbo.spring_app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.domain.model.User;
import com.fibbo.spring_app.domain.repository.BookingRepository;
import com.fibbo.spring_app.domain.repository.SeatRepository;
import com.fibbo.spring_app.domain.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class BookingControllerTest {

   // Variável para armazenar o valor do cookie JWT extraído no login
   private String jwtCookieValue;


    @Autowired
    private  PasswordEncoder passwordEncoder; 


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SeatRepository seatRepository;


    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private ObjectMapper objectMapper;


    @SuppressWarnings("resource")
    @Container
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest"))
            .withDatabaseName("desafio")
            .withUsername("user")
    .withPassword("password");


    /*
    * Definição das propriedades
    */
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }


    @AfterAll
    static void afterAll() {
       postgres.close();
    }


    @PostConstruct
    void setUp() throws Exception {

        if (userRepository.findByUsername("user1").isEmpty()) {
            
            User admin = new User();
            admin.setUsername("user1");
            admin.setPassword(passwordEncoder.encode("user123"));
            admin.setRole("ADMIN");
        }
        
    }



    @BeforeEach
    public void setUpLogin() throws Exception {
        String userJson = 
        """
            {
                "username": "user1",
                "password": "user123"
            }
        """;

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
            .andExpect(status().isOk())
            .andReturn();

      
        Cookie jwtCookie = loginResult.getResponse().getCookie("jwt");
        if (jwtCookie != null) {
            jwtCookieValue = jwtCookie.getValue();
        } else {
            throw new IllegalStateException("JWT cookie não foi encontrado na resposta de login.");
        }
    }

   @Test
   @Order(1)
   public void testContainerIsRunning() throws SQLException {
       try (Connection connection = DriverManager.getConnection(
               postgres.getJdbcUrl(),
               postgres.getUsername(),
               postgres.getPassword())) {
           assertNotNull(connection);
       }
    }


    @Test
    @Order(2)
    public void shouldCreateBookingSuccessfully() throws Exception {

        Seat seat = new Seat();
        seat.setOcupada(false);

        seatRepository.save(seat);

        String seatId = seat.getId().toString();
        
        BookingDTO bookingDTO = new BookingDTO(seatId, "ALOCACAO");

        mockMvc.perform(post("/api/booking/")
        .contentType(MediaType.APPLICATION_JSON)
        .cookie(new Cookie("jwt", jwtCookieValue))
        .content(objectMapper.writeValueAsString(bookingDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.user.username").value("user1"))
        .andExpect(jsonPath("$.seat.id").value(seatId))
        .andExpect(jsonPath("$.seat.ocupada").value(true))
        .andExpect(jsonPath("$.acao").value("ALOCACAO"));

    }

    @Test
    @Order(3)
    void shouldUpdateBookingSuccessfully() throws Exception {

        Seat seat = new Seat();
        seat.setOcupada(false);
        seatRepository.save(seat);
        String seatId = seat.getId().toString();

        Booking booking = new Booking();
        booking.setAcao(Booking.StatusBooking.ALOCACAO);
        booking.setSeat(seat);
        User user = userRepository.findByUsername("user1").get();
        booking.setUser(user);
        bookingRepository.save(booking);


        BookingDTO bookingDTO = new BookingDTO(seatId, "REMOCAO");
        String bookingId = booking.getId().toString(); 

        mockMvc.perform(put("/api/booking/")
        .param("id", bookingId)
        .contentType(MediaType.APPLICATION_JSON)
        .cookie(new Cookie("jwt", jwtCookieValue))
        .content(objectMapper.writeValueAsString(bookingDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.user.username").value("user1"))
        .andExpect(jsonPath("$.seat.id").value(seatId))
        .andExpect(jsonPath("$.seat.ocupada").value(false))
        .andExpect(jsonPath("$.acao").value("REMOCAO"));

    }
}

package com.fibbo.spring_app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fibbo.spring_app.config.ExceptionHandler.CustomException;
import com.fibbo.spring_app.domain.dto.BookingDTO;
import com.fibbo.spring_app.domain.model.Booking;
import com.fibbo.spring_app.domain.model.Seat;
import com.fibbo.spring_app.domain.repository.BookingRepository;
import com.fibbo.spring_app.domain.repository.SeatRepository;
import com.fibbo.spring_app.service.BookingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService  {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;


    @Override
    public Booking createBooking(BookingDTO bookingRequest){

        List<String> listSeatsRequest = bookingRequest.seats();
        List<String> listSeatsInvalidos = findSeatsByIdInvalid(listSeatsRequest);

        if (listSeatsInvalidos.size() > 0) {
            
            throw new CustomException("Os seguintes assentos se encontram reservados: " + listSeatsInvalidos
            , HttpStatus.BAD_REQUEST);
        }
       

        Booking booking = new Booking();
        booking.setStatus(Booking.StatusBooking.valueOf(bookingRequest.status()));

        List<Seat> listSeats = findSeatsById(listSeatsRequest);
        
        booking.setSeats(listSeats);
        bookingRepository.save(booking);


        return booking;
    }

    @Override
    public Booking updateBooking(String uuid, String status) {

        UUID bookingId = UUID.fromString(uuid);
        Booking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new CustomException("Reserva não encontrada para o id: " + uuid, HttpStatus.BAD_REQUEST));
    
       
        booking.setStatus(Booking.StatusBooking.valueOf(status));
    
     
        booking.getSeats().forEach(seat -> seat.setStatus(Seat.StatusSeat.DISPONÍVEL));
    
        return bookingRepository.save(booking);
    }

    /**
     * Retorna uma lista de identificadores de assentos (em formato String) que estão com o status RESERVADO.
     *
     * @param seats Lista de identificadores de assentos (em formato String)
     * @return Lista de identificadores (String) dos assentos que estão com status RESERVADO
     */
    private List<String> findSeatsByIdInvalid(List<String> seats) {
        return seats.stream()
            .map(seat -> seatRepository.findById(UUID.fromString(seat)))
            .flatMap(Optional::stream) 
            .filter(seat -> seat.getStatus().equals(Seat.StatusSeat.RESERVADO)) 
            .map(seat -> seat.getId().toString())
        .toList(); 
    }

    /**
     * Busca os assentos a partir de uma lista de identificadores (em formato String), 
     * atualiza o status de cada assento para RESERVADO e retorna a lista dos assentos atualizados.
     *
     * @param seats Lista de identificadores de assentos (em formato String)
     * @return Lista de objetos Seat com o status atualizado para RESERVADO
     * @throws CustomException se algum assento não for encontrado no repositório
     */
    @Transactional
    private List<Seat> findSeatsById(List<String> seats) {
        return seats.stream()
            .map(seat -> {
                Seat seatFound = seatRepository.findById(UUID.fromString(seat))
                    .orElseThrow(() -> new CustomException("Assento não encontrado para o id: " + seat, HttpStatus.BAD_REQUEST));
                seatFound.setStatus(Seat.StatusSeat.RESERVADO);
                return seatFound; 
            })
        .toList(); 
    }


}

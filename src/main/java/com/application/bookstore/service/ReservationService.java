package com.application.bookstore.service;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Reservation;
import com.application.bookstore.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReservationService {
    ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public List<Reservation> findAll(){
        log.info("Display books in reservation list");
        return reservationRepository.findAll();
    }

    public List<Book> findBooksInReservation(){
        log.info("Display books in reservation list");
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .flatMap(reservation -> reservation.getBooks().stream())
                .filter(book->!book.isReturned)
                .collect(Collectors.toList());

    }

}

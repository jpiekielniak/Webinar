package org.example.webinar.bmpn.api.utils;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.example.webinar.bmpn.api.entity.Webinar;
import org.example.webinar.bmpn.api.repository.ReservationRepository;
import org.example.webinar.bmpn.api.repository.WebinarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private WebinarRepository webinarRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void run(String... args) {
        webinarRepository.save(
                Webinar.builder()
                        .price(2d)
                        .title("Programowanie w Haskell")
                        .description("Dla sadystów")
                        .remainingSlots(20)
                        .reservations(Collections.emptyList())
                        .completed(false)
                        .build()
        );
        webinarRepository.save(
                Webinar.builder()
                        .price(120.50d)
                        .title("Nowoczesny HTML")
                        .description("Potężny HTML dla nowicjiuszy")
                        .remainingSlots(50)
                        .reservations(Collections.emptyList())
                        .completed(false)
                        .build()
        );
        webinarRepository.save(
                Webinar.builder()
                        .price(20d)
                        .title("Docker w praktyce")
                        .description("Dla sadystów")
                        .remainingSlots(20)
                        .reservations(Collections.emptyList())
                        .completed(false)
                        .build()
        );
        webinarRepository.save(
                Webinar.builder()
                        .price(20d)
                        .title("Azure od postaw")
                        .description("Dla sadystów")
                        .remainingSlots(1)
                        .reservations(Collections.emptyList())
                        .completed(false)
                        .build()
        );
        webinarRepository.save(
                Webinar.builder()
                        .price(20d)
                        .title("Mikroserwisy w .NET")
                        .description("Dla sadystów")
                        .remainingSlots(20)
                        .reservations(Collections.emptyList())
                        .completed(false)
                        .build()
        );
        webinarRepository.save(
                Webinar.builder()
                        .price(20d)
                        .title("Databricks - wprowadzenie")
                        .description("Dla sadystów")
                        .remainingSlots(0)
                        .reservations(Collections.emptyList())
                        .completed(false)
                        .build()
        );

        reservationRepository.save(
                Reservation.builder()
                        .email("jakubpiekielniak123@gmail.com")
                        .firstName("Jakub")
                        .lastName("Piekielniak")
                        .webinar(webinarRepository.getById(1L))
                        .status(ReservationStatus.COMPLETED)
                        .build()
        );

        reservationRepository.save(
                Reservation.builder()
                        .email("arkadiusz.kupiec98@gmail.com")
                        .firstName("Arkadiusz")
                        .lastName("Kupiec")
                        .webinar(webinarRepository.getById(1L))
                        .status(ReservationStatus.COMPLETED)
                        .build()
        );

        System.out.println("Dane zostały zainicjalizowane w bazie danych.");
    }
}
package org.example.webinar.bmpn.api.repository;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Znajdź wszystkie rezerwacje dla danego webinaru
    List<Reservation> findByWebinarId(Long webinarId);
}
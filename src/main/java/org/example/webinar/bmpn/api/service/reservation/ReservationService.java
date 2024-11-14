package org.example.webinar.bmpn.api.service.reservation;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.ReservationStatus;

import java.util.Optional;

public interface ReservationService {
    Optional<Long> addReservation(Reservation reservation);
    void changeReservationStatus(Long reservationId, ReservationStatus newStatus);
    void deleteReservation(Long reservationId);
}

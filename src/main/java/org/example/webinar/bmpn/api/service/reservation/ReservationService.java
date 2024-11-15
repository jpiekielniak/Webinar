package org.example.webinar.bmpn.api.service.reservation;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

public interface ReservationService {
    Optional<Long> addReservation(Reservation reservation);
    Optional<Reservation> getReservation(Long reservationId);
    void changeReservationStatus(Long reservationId, ReservationStatus newStatus);
    void deleteReservation(Long reservationId);
    SseEmitter addListener(String listenerId);
    void sendMessageToListener(String listenerId, String listener);
    void removeListener(String listenerId);
}

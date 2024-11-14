package org.example.webinar.bmpn.api.service.reservation;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Override
    public Optional<Long> addReservation(Reservation reservation) {
        return Optional.of(1L);
    }

    @Override
    public void changeReservationStatus(Long reservationId, ReservationStatus newStatus) {
    }

    @Override
    public void deleteReservation(Long reservationId) {

    }
}

package org.example.webinar.bmpn.api.service.reservation;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.example.webinar.bmpn.api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Optional<Long> addReservation(Reservation reservation) {
        Reservation instance = reservationRepository.save(reservation);
        return Optional.of(instance.getId());
    }

    @Override
    public Optional<Reservation> getReservation(Long reservationId) {
        return Optional.of(reservationRepository.getById(reservationId));
    }

    @Override
    @Transactional
    public void changeReservationStatus(Long reservationId, ReservationStatus newStatus) {
        reservationRepository.getById(reservationId).setStatus(newStatus);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        reservationRepository.delete(reservationRepository.getById(reservationId));
    }
}

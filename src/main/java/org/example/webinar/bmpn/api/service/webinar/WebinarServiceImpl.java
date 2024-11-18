package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.Webinar;
import org.example.webinar.bmpn.api.model.ParticipantInfo;
import org.example.webinar.bmpn.api.repository.ReservationRepository;
import org.example.webinar.bmpn.api.repository.WebinarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WebinarServiceImpl implements WebinarService {
    @Autowired
    private WebinarRepository webinarRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Optional<Boolean> isFreeSlot(Long webinarId) {
        return Optional.of(webinarRepository.getById(webinarId).getRemainingSlots() > 0);
    }

    @Override
    public Optional<Webinar> getById(Long webinarId) {
        return Optional.of(webinarRepository.getById(webinarId));
    }

    @Override
    public Optional<List<Reservation>> getReservationList(Long webinarId) {
        return Optional.of(reservationRepository.findByWebinarId(webinarId));
    }

    @Override
    public List<Webinar> getWebinarList() {
        return webinarRepository.findAll();
    }

    @Override
    public List<ParticipantInfo> getWebinarParticipants(Long webinarId) {
        return webinarRepository.getById(webinarId)
                .getReservations()
                .stream()
                .map(reservation -> new ParticipantInfo(reservation.getFirstName() + " " + reservation.getLastName(), reservation.getEmail())).toList();
    }

    @Override
    @Transactional
    public void changeWebinarStatus(Long webinarId, boolean isCompleted) {
        webinarRepository.getById(webinarId).setCompleted(isCompleted);
    }
}

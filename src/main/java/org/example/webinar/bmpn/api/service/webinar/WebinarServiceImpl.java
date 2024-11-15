package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.Webinar;
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
}

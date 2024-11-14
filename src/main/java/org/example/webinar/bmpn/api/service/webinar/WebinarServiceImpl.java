package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.Webinar;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebinarServiceImpl implements WebinarService {
    @Override
    public Optional<Boolean> isFreeSlot(Long webinarId) {
        return Optional.of(true);
    }

    @Override
    public Optional<Webinar> getById(Long webinarId) {
        return Optional.of(new Webinar());
    }

    @Override
    public Optional<Reservation> getReservation(Long webinarId) {
        return Optional.empty();
    }
}

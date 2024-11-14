package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.Webinar;

import java.util.Optional;

public interface WebinarService {
    Optional<Boolean> isFreeSlot(Long webinarId);
    Optional<Webinar> getById(Long webinarId);
    Optional<Reservation> getReservation(Long webinarId);
}

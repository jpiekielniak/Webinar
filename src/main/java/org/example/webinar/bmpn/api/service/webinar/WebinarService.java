package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.model.request.PrereservationRequest;

public interface WebinarService {
    boolean isFreeSlot(Long webinarId);
    String completeReservation(Long reservationId);
    Long preBookReservation(PrereservationRequest prereservationRequest);
    void deleteReservation(Long reservationId);
}

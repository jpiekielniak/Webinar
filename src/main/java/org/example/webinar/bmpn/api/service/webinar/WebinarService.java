package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.model.request.PrereservationRequest;
import org.example.webinar.bmpn.api.model.response.PrereservationResponse;

public interface WebinarService {
    boolean checkIfFreeSlotExists();
    String completeReservation(Long reservationId);
    Long preBookReservation(PrereservationRequest prereservationRequest);
    void deleteReservation(Long reservationId);

}

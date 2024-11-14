package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.model.request.PrereservationRequest;
import org.springframework.stereotype.Service;

@Service
public class WebinarServiceImpl implements WebinarService {
    @Override
    public boolean isFreeSlot(Long webinarId) {
        return true;
    }

    @Override
    public String completeReservation(Long reservationId) {
        return null;
    }

    @Override
    public Long preBookReservation(PrereservationRequest prereservationRequest) {
        return 1L;
    }

    @Override
    public void deleteReservation(Long reservationId) {

    }
}

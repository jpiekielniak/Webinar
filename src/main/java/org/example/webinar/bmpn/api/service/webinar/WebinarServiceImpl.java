package org.example.webinar.bmpn.api.service.webinar;

import org.example.webinar.bmpn.api.model.request.PrereservationRequest;
import org.example.webinar.bmpn.api.model.response.PrereservationResponse;
import org.springframework.stereotype.Service;

@Service
public class WebinarServiceImpl implements WebinarService {
    @Override
    public boolean checkIfFreeSlotExists() {
        return false;
    }

    @Override
    public String completeReservation(Long reservationId) {
        return null;
    }

    @Override
    public Long preBookReservation(PrereservationRequest prereservationRequest) {
        return null;
    }

    @Override
    public void deleteReservation(Long reservationId) {

    }
}

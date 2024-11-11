package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.example.webinar.bmpn.api.model.request.PrereservationRequest;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PreBookingWorker {

    @Autowired
    private WebinarService webinarService;

    @JobWorker(type = "preBooking")
    public HashMap<String, Object> preBooking(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        //Logika biznesowa - zarezerwowanie miejsca (utworzenie rezerwacji w bazie danych ze statusem pre-booking)
        // Zwrócenie ID rezerwacji w zmiennej "reservationId" do dalszego przetwarzania
        PrereservationRequest reservationData = PrereservationRequest.builder()
                .name((String)job.getVariablesAsMap().get("name"))
                .surname((String)job.getVariablesAsMap().get("surname"))
                .email((String)job.getVariablesAsMap().get("email"))
                .webinarId((Long)job.getVariablesAsMap().get("webinarId"))
                .build();
        Long reservationId = webinarService.preBookReservation(reservationData);
        return jobResultVariables;
    }
}

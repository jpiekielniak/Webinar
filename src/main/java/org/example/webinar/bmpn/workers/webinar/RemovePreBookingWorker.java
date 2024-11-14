package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RemovePreBookingWorker {

    @Autowired
    private WebinarService webinarService;

    @JobWorker(type = "removePreBooking")
    public Map<String, Object> removePreBooking(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - usunięcie rezerwacji z bazy danych
        webinarService.deleteReservation((Long) job.getVariablesAsMap().get("reservationId"));
        return jobResultVariables;
    }
}

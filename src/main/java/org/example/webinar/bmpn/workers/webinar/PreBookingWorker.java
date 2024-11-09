package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PreBookingWorker {

    @JobWorker(type = "preBooking")
    public HashMap<String, Object> preBooking(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        //Logika biznesowa - zarezerwowanie miejsca (utworzenie rezerwacji w bazie danych ze statusem pre-booking)
        // Zwrócenie ID rezerwacji w zmiennej "reservationId" do dalszego przetwarzania

        return jobResultVariables;
    }
}

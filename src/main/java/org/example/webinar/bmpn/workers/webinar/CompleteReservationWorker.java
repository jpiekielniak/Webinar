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
public class CompleteReservationWorker {

    @Autowired
    private WebinarService webinarService;

    @JobWorker(type = "completeReservation")
    public Map<String, Object> completeReservation(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        //Logika biznesowa - zakończenie rezerwacji (zmiana statusu rezerwacji w bazie danych na completed)
        // Zwrócić ewentualnie dla dalszego procesu adres e-mail
        Long reservationId = (Long) job.getVariablesAsMap().get("reservationId");
        String email = webinarService.completeReservation(reservationId);

        return jobResultVariables;
    }
}

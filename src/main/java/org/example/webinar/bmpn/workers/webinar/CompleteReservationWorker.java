package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CompleteReservationWorker {

    private WebinarService webinarService;

    @JobWorker(type = "completeReservation")
    public Map<String, Object> completeReservation(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        //Logika biznesowa - zakończenie rezerwacji (zmiana statusu rezerwacji w bazie danych na completed)
        // Zwrócić ewentualnie dla dalszego procesu adres e-mail
        Long reservationId = (Long) job.getVariablesAsMap().get("reservationId");
        String email = webinarService.completeReservation(reservationId);

        return jobResultVariables;
    }
}

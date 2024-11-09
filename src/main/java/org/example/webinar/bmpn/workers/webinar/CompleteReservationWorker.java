package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CompleteReservationWorker {

    @JobWorker(type = "completeReservation")
    public HashMap<String, Object> completeReservation(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - zakończenie rezerwacji (zmiana statusu rezerwacji w bazie danych na completed)
        // Zwrócić ewentualnie dla dalszego procesu adres e-mail

        return jobResultVariables;
    }
}

package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SendConfirmReservationEmailWorker {

    @JobWorker(type = "sendConfirmReservationEmail")
    public HashMap<String, Object> sendConfirmReservationEmail(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - wysłanie e-maila z potwierdzeniem rezerwacji

        return jobResultVariables;
    }
}

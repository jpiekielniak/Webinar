package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SendFailureEmailWorker {

    @JobWorker(type = "sendFailureEmail")
    public HashMap<String, Object> sendFailureEmail(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        //Logika biznesowa - wysłanie maila z informacją o niepowodzeniu (jakiś email sender service)
        // Zwrócenie wartości true w zmiennej "emailSent"

        //jobResultVariables.put("emailSent", true);
        return jobResultVariables;
    }
}

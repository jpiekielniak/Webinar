package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SendConfirmationPaymentEmailWorker {

    @JobWorker(type = "sendConfirmationPaymentEmail")
    public Map<String, Object> sendConfirmationPaymentEmail(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - wysłanie e-maila z potwierdzeniem płatności
        return jobResultVariables;
    }
}

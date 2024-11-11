package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SendRejectedPaymentEmail {

    @JobWorker(type = "sendRejectedPaymentEmail")
    public HashMap<String, Object> validateCreditCardData(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - wysłanie e-mail z informacją o odrzuceniu płatności
        return jobResultVariables;
    }
}

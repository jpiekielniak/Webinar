package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ValidateCreditCardDataWorker {

    @JobWorker(type = "validateCreditCardData")
    public HashMap<String, Object> validateCreditCardData(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - walidacja danych karty kredytowej
        // Zwrócenie wyniku walidacji w zmiennej "isCreditCardDataValid" do dalszego przetwarzania
        return jobResultVariables;
    }
}

package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.example.webinar.bmpn.api.model.request.CreditCard;
import org.example.webinar.bmpn.api.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateCreditCardDataWorker {

    @Autowired
    private PaymentService paymentService;

    @JobWorker(type = "validateCreditCardData")
    public Map<String, Object> validateCreditCardData(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - walidacja danych karty kredytowej
        // Zwrócenie wyniku walidacji w zmiennej "isCreditCardDataValid" do dalszego przetwarzania
        CreditCard creditCard = CreditCard.builder()
                .name((String)job.getVariablesAsMap().get("name"))
                .surname((String)job.getVariablesAsMap().get("surname"))
                .code((String) job.getVariablesAsMap().get("code"))
                .expirationDate((String)job.getVariablesAsMap().get("expirationDate"))
                .build();
        paymentService.checkIsCreditCardValid(creditCard);
        jobResultVariables.put("isCreditCardDataValid", false);

        return jobResultVariables;
    }
}

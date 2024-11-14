package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.model.request.CreditCard;
import org.example.webinar.bmpn.api.service.payment.PaymentService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ValidateCreditCardDataWorker {

    private PaymentService paymentService;

    @JobWorker(type = "validateCreditCardData")
    public Map<String, Object> validateCreditCardData(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        CreditCard creditCard = CreditCard.builder()
                .name((String) job.getVariablesAsMap().get("firstName"))
                .surname((String) job.getVariablesAsMap().get("lastName"))
                .code((String) job.getVariablesAsMap().get("code"))
                .expirationDate((String) job.getVariablesAsMap().get("expirationDate"))
                .build();
        paymentService.checkIsCreditCardValid(creditCard);
        jobResultVariables.put("isCreditCardDataValid", false);

        return jobResultVariables;
    }
}

package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.email.EmailService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class SendRejectedPaymentEmailWorker {

    private EmailService emailService;

    @JobWorker(type = "sendRejectedPaymentEmail")
    public Map<String, Object> sendRejectedPaymentEmail(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var email = jobResultVariables.get("email").toString();
        emailService.sendRejectedPaymentEmail(email);

        jobResultVariables.put("isPaymentSuccessful", false);

        return jobResultVariables;
    }
}

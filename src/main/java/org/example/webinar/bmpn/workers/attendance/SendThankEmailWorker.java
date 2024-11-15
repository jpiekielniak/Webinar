package org.example.webinar.bmpn.workers.attendance;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.email.EmailService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class SendThankEmailWorker {

    private EmailService emailService;

    @JobWorker(type = "sendThankEmail")
    public Map<String, Object> sendThankEmail(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();
        final var emails = (List<String>) jobResultVariables.get("emails");

        try {
            emails.forEach(email -> emailService.sendThankEmail(email));
        } catch (Exception e) {
            client.newThrowErrorCommand(job.getKey())
                    .errorCode("SEND_THANK_EMAIL_FAILURE")
                    .send()
                    .join();
        }

        return jobResultVariables;
    }
}
